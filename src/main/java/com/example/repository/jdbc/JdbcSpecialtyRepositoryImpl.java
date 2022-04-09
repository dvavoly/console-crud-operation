package com.example.repository.jdbc;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.utils.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcSpecialtyRepositoryImpl implements SpecialtyRepository {

    private final DatasourceFactory factory = new DatasourceFactory();

    private final Logger LOGGER = LoggerFactory.getLogger(JdbcSpecialtyRepositoryImpl.class);

    @Override
    public Specialty getById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        String query = "SELECT * FROM specialty WHERE id = " + id;
        try (var statement = factory.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.first()) {
                return new Specialty(resultSet.getInt("id"),
                        resultSet.getString("specialty_name"));
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
        return null;
    }

    @Override
    public List<Specialty> getAll() {
        List<Specialty> result = new ArrayList<>();
        String query = "SELECT * FROM specialty";
        try (var statement = factory.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(new Specialty(
                        resultSet.getInt("id"),
                        resultSet.getString("specialty_name")));
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
        return result;
    }

    @Override
    public Specialty save(Specialty specialty) {

        if (specialty.getName() == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        String query = "INSERT INTO specialty (specialty_name) VALUES ('" + specialty.getName() + "')";
        String getLastId = "SELECT * FROM specialty ORDER BY id DESC LIMIT 1";
        try (var statement = factory.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.execute(query);
            ResultSet resultSet = statement.executeQuery(getLastId);
            if (resultSet.first()) {
                return new Specialty(
                        resultSet.getInt("id"),
                        resultSet.getString("specialty_name"));
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
        return null;
    }

    @Override
    public Specialty update(Specialty specialty) {

        if (specialty.getId() == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        String query = "UPDATE specialty SET specialty_name = '" + specialty.getName() +
                "' WHERE id = " + specialty.getId();
        try (var statement = factory.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
        return getById(specialty.getId());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        String query = "DELETE FROM specialty WHERE id = " + id;
        try (var statement = factory.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
    }
}
