package com.example.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Specialty;
import com.example.repository.SpecialtyRepository;
import com.example.utils.JdbcUtils;
import com.example.utils.Messages;
import com.example.utils.SqlQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcSpecialtyRepositoryImpl implements SpecialtyRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(JdbcSpecialtyRepositoryImpl.class);

    @Override
    public Specialty getById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        try (var statement = JdbcUtils.getPreparedStatement(SqlQuery.getSpecialtyById.toString())) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
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
        try (var statement = JdbcUtils.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.getAllSpecialties.toString());
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
        //
        try (var insertSpecialty = JdbcUtils.getPreparedStatement(SqlQuery.insertSpecialty.toString());
             var statement = JdbcUtils.getConnection().createStatement()) {
            insertSpecialty.setString(1, specialty.getName());
            insertSpecialty.executeUpdate();
            ResultSet resultSet = statement.executeQuery(SqlQuery.getLastSpecialty.toString());
            if (resultSet.next()) {
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

        if (specialty.getId() == null && specialty.getName() == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        try (var statement = JdbcUtils.getPreparedStatement(SqlQuery.updateSpecialty.toString())) {
            statement.setString(1, specialty.getName());
            statement.setInt(2, specialty.getId());
            statement.executeUpdate();
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

        try (var statement = JdbcUtils.getPreparedStatement(SqlQuery.deleteSpecialty.toString())) {
            statement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
    }
}
