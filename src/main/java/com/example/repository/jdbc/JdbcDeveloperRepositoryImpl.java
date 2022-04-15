package com.example.repository.jdbc;

import com.example.model.Developer;
import com.example.model.Skill;
import com.example.model.Specialty;
import com.example.model.Status;
import com.example.repository.DeveloperRepository;
import com.example.utils.JdbcUtils;
import com.example.utils.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.utils.JdbcUtils.getPreparedStatement;
import static com.example.utils.SqlQuery.*;
import static com.example.utils.SqlQuery.getAllDevelopers;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcDeveloperRepositoryImpl.class);

    @Override
    public Developer getById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        Developer result = Developer.of();

        try (var statement = getPreparedStatement(getDeveloperById.toString())) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setFirstName(resultSet.getString("first_name"));
                result.setLastName(resultSet.getString("last_name"));
                result.setStatus(Status.valueOf(resultSet.getString("status")));
                result.setSpecialty(new Specialty(
                        resultSet.getInt("specialty_id"),
                        resultSet.getString("specialty_name")));
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }

        try (var statement = getPreparedStatement(getSkillsByDeveloperId.toString())) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Skill> skills = new ArrayList<>();
            while (resultSet.next()) {
                skills.add(new Skill(resultSet.getString("skill_name"), resultSet.getInt("skill_id")));
            }
            result.setSkills(skills);
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
        return result;
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> output = new ArrayList<>();
        try (var getAll = getPreparedStatement(getAllDevelopers.toString());
             var getSkill = getPreparedStatement(getSkillByDeveloperId.toString())) {
            var resultSet = getAll.executeQuery();
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
                getSkill.setInt(1, developer.getId());
                ResultSet resultSkillSet = getSkill.executeQuery();
                while (resultSkillSet.next()) {
                    skillList.add(new Skill(
                            resultSkillSet.getString("skill_name"),
                            resultSkillSet.getInt("skill_id")));
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
            throw new AssertionError("Unable to save developer");
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

        try (var statement = JdbcUtils.getPreparedStatement(deleteDeveloperById.toString())) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
    }

    private static int saveFirstAndLastName(String firstName, String lastName) {

        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("Developer's first or last name cannot be null.");
        }
        try (var preparedStatement = JdbcUtils.getPreparedStatement(insertDeveloper.toString());
             var statement = JdbcUtils.getConnection().createStatement()) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.execute();
            ResultSet resultSet = statement.executeQuery(getLastDeveloperId.toString());
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Exception: ", exception);
        }
        return -1;
    }

    private static void saveSpecialty(Specialty specialty, int developerId) {
        int specialtyId;
        try (var statement = JdbcUtils.getPreparedStatement(getSpecialtyId.toString());
             var insetDev = JdbcUtils.getPreparedStatement(insertDeveloperSpecialty.toString())) {
            statement.setString(1, specialty.getName());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                specialtyId = resultSet.getInt("id");
            } else {
                throw new IllegalArgumentException("Cannot find a specialty, need to create one first.");
            }
            insetDev.setInt(1, developerId);
            insetDev.setInt(2, specialtyId);
            insetDev.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
    }

    private static void saveSkills(List<Skill> skills, int developerId) {
        try (var preparedStatement = JdbcUtils.getPreparedStatement(insertDeveloperSkill.toString())) {
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
