package com.example.repository.jdbc;

import com.example.model.Skill;
import com.example.repository.SkillRepository;
import com.example.utils.JdbcUtils;
import com.example.utils.Messages;
import com.example.utils.SqlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcSkillRepositoryImpl implements SkillRepository {

    Logger LOGGER = LoggerFactory.getLogger(JdbcSkillRepositoryImpl.class);

    @Override
    public Skill getById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        try (var statement = JdbcUtils.getPreparedStatement(SqlQuery.getSkillById.toString())) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Skill(
                        resultSet.getString("skill_name"),
                        resultSet.getInt("id"));
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Error: ", exception);
        }
        return null;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try (var statement = JdbcUtils.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.getAllSkill.toString());
            while (resultSet.next()) {
                result.add(new Skill(
                        resultSet.getString("skill_name"),
                        resultSet.getInt("id")));
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Error: ", exception);
        }
        return result;
    }

    @Override
    public Skill save(Skill skill) {

        if (skill.getName() == null) {
            throw new IllegalArgumentException("Skill name cannot be null.");
        }

        try (var statement = JdbcUtils.getPreparedStatement(SqlQuery.insertSkill.toString());
             var getSkill = JdbcUtils.getConnection().createStatement()) {
            statement.setString(1, skill.getName());
            statement.execute();
            ResultSet resultSet = getSkill.executeQuery(SqlQuery.getLastSkill.toString());
            if (resultSet.next()) {
                return new Skill(
                        resultSet.getString("skill_name"),
                        resultSet.getInt("id"));
            }
        } catch (SQLException exception) {
            LOGGER.error("SQL Error", exception);
        }
        return null;
    }

    @Override
    public Skill update(Skill skill) {

        if (skill.getId() == null && skill.getName() == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        try (var statement = JdbcUtils.getPreparedStatement(SqlQuery.updateSkill.toString())) {
            statement.setString(1, skill.getName());
            statement.setInt(2, skill.getId());
            statement.execute();
        } catch (SQLException exception) {
            LOGGER.error("SQL Error: ", exception);
        }
        return getById(skill.getId());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException(Messages.CANNOT_BE_NULL.toString());
        }

        try (var statement = JdbcUtils.getPreparedStatement(SqlQuery.deleteSkill.toString())) {
            statement.execute();
        } catch (SQLException exception) {
            LOGGER.error("SQL Error: ", exception);
        }
    }
}
