package com.example.repository.jdbc;

import com.example.model.Developer;
import com.example.model.Skill;
import com.example.model.Specialty;
import com.example.model.Status;
import com.example.repository.DeveloperRepository;
import com.example.utils.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {

    private final static DatasourceFactory factory = new DatasourceFactory();
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcDeveloperRepositoryImpl.class);

    @Override
    public Developer getById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        Developer result = Developer.of();
        String queryDeveloper = "SELECT id, first_name, last_name FROM developer WHERE developer.id = ";
        String querySkills = """
                SELECT skill_id, skill_name
                FROM developer_skill
                JOIN skill s on developer_skill.skill_id = s.id
                WHERE developer_id =
                """;
        String querySpecialty = """
                SELECT specialty_id, specialty_name
                FROM specialty
                JOIN developer_specialty ds on specialty.id = ds.specialty_id
                WHERE developer_id =
                """;

        try (var statement = factory.getConnection().createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = statement.executeQuery(queryDeveloper + id);
            if (resultSet.first()) {
                result.setId(resultSet.getInt("id"));
                result.setFirstName(resultSet.getString("first_name"));
                result.setLastName(resultSet.getString("last_name"));
            }
            resultSet = statement.executeQuery(querySkills + id);
            List<Skill> skills = new ArrayList<>();
            while (resultSet.next()) {
                skills.add(new Skill(resultSet.getString("skill_name"), resultSet.getInt("skill_id")));
            }
            result.setSkills(skills);
            resultSet = statement.executeQuery(querySpecialty + id);
            if (resultSet.first()) {
                result.setSpecialty(new Specialty(
                        resultSet.getInt("specialty_id"),
                        resultSet.getString("specialty_name")));
            }

        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
        return result;
    }

    @Override
    public List<Developer> getAll() {

        String firstLastNameAndSpecialty = """
                SELECT developer.id, first_name, last_name, specialty_name, status
                FROM developer
                LEFT JOIN developer_specialty ON developer.id = developer_specialty.developer_id
                LEFT JOIN specialty ON specialty.id = developer_specialty.specialty_id;
                """;
        String skills = """
                SELECT skill_name
                FROM developer_skill
                JOIN skill ON developer_skill.skill_id = skill.id
                WHERE developer_id =
                        """;
        List<Developer> output = new ArrayList<>();
        try (var statement = factory.getConnection().createStatement()) {
            var resultSet = statement.executeQuery(firstLastNameAndSpecialty);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String specialty_name = resultSet.getString("specialty_name");
                Status status = Status.valueOf(resultSet.getString("status"));
                output.add(Developer.of(id, first_name, last_name, specialty_name, status));
            }

            for (Developer developer : output) {
                List<Skill> skillList = new ArrayList<>();
                ResultSet skillListSet = statement.executeQuery(skills + developer.getId());
                while (skillListSet.next()) {
                    skillList.add(new Skill(skillListSet.getString("skill_name")));
                }
                developer.setSkills(skillList);
            }

        } catch (
                SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
        return output;
    }

    @Override
    public Developer save(Developer developer) {

        int developerId = saveFirstAndLastName(developer.getFirstName(), developer.getLastName());
        if (developerId == -1) {
            throw new AssertionError("Cannot save developer");
        }
        saveSpecialty(developer.getSpecialty(), developerId);
        saveSkills(developer.getSkills(), developerId);
        return getById(developerId);
    }

    @Override
    public Developer update(Developer developer) {

        if (developer == null || developer.getId() == null) {
            throw new IllegalArgumentException("Developer or developer id cannot be null.");
        }

        save(developer);
        return getById(developer.getId());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        String query = "UPDATE developer SET status = 'DELETED' WHERE id = ";
        try (var connection = factory.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(query + id);
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
    }

    private static int saveFirstAndLastName(String firstName, String lastName) {

        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("Developer's first or last name cannot be null.");
        }

        String queryCreateDeveloper =
                "INSERT INTO developer(first_name, last_name) VALUES ('" + firstName + "', '" + lastName + "')";

        String queryGetLastId = "SELECT id FROM developer ORDER BY id DESC LIMIT 1;";

        try (var statement = factory.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.execute(queryCreateDeveloper);
            ResultSet resultSet = statement.executeQuery(queryGetLastId);
            if (resultSet.first()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Exception: ", exception);
        }
        return -1;
    }

    private static void saveSpecialty(Specialty specialty, int developerId) {
        String queryGetIdOfSpecialty = "SELECT id FROM specialty WHERE specialty_name = '" + specialty.getName() + "'";
        int specialtyId;
        try (var statement = factory.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = statement.executeQuery(queryGetIdOfSpecialty);
            if (resultSet.first()) {
                specialtyId = resultSet.getInt("id");
            } else {
                throw new IllegalArgumentException("Cannot find a specialty, need to create one first.");
            }
            statement.execute("INSERT INTO developer_specialty VALUES ('" + developerId + "', '" + specialtyId + "')");
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
    }

    private static void saveSkills(List<Skill> skills, int developerId) {
        String query = "INSERT INTO developer_skill(developer_id, skill_id) VALUES( ?, ?)";
        try (var preparedStatement = factory.getConnection().prepareStatement(query)) {
            for (Skill item : skills) {
                preparedStatement.setInt(1, developerId);
                preparedStatement.setInt(2, item.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
    }
}
