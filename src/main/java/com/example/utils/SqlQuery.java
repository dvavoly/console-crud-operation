package com.example.utils;

public enum SqlQuery {

    getDeveloperById("""
            SELECT developer.id, first_name, last_name, specialty_id, specialty_name, status
            FROM developer
                LEFT JOIN developer_specialty ON developer.id = developer_specialty.developer_id
                LEFT JOIN specialty ON specialty.id = developer_specialty.specialty_id
            WHERE developer.id = ?
            """),

    getSkillsByDeveloperId("""
            SELECT skill_id, skill_name
            FROM developer_skill JOIN skill s on developer_skill.skill_id = s.id
            WHERE developer_id = ?
            """),

    getAllDevelopers("""
            SELECT developer.id, first_name, last_name, specialty_name, status
            FROM developer
                LEFT JOIN developer_specialty ON developer.id = developer_specialty.developer_id
                LEFT JOIN specialty ON specialty.id = developer_specialty.specialty_id;
            """),

    getSkillByDeveloperId("""
            SELECT skill_name, skill_id
            FROM developer_skill
                JOIN skill ON developer_skill.skill_id = skill.id
            WHERE developer_id = ?
            """),
    getById("SELECT * FROM ? WHERE id = ?"),
    getSkillById("SELECT * FROM skill WHERE id = ?"),
    getSpecialtyById("SELECT * FROM specialty WHERE id = ?"),
    getAll("SELECT * FROM ?"),
    getAllSkill("SELECT * FROM skill"),
    getAllSpecialties("SELECT * FROM specialty"),
    insertDeveloper("INSERT INTO developer(first_name, last_name) VALUES (?, ?)"),
    insertDeveloperSkill("INSERT INTO developer_skill(developer_id, skill_id) VALUES(?, ?)"),
    insertDeveloperSpecialty("INSERT INTO developer_specialty VALUES (?, ?)"),
    insertSkill("INSERT INTO skill (skill_name) VALUES (?)"),
    insertSpecialty("INSERT INTO specialty(specialty_name) VALUES(?)"),
    getLastId("SELECT id FROM ? ORDER BY id DESC LIMIT 1"),
    getLastDeveloperId("SELECT id FROM developer ORDER BY id DESC LIMIT 1;"),
    getLastSkill("SELECT * FROM skill ORDER BY id LIMIT 1"),
    getLastSpecialty("SELECT * FROM specialty ORDER BY id DESC LIMIT 1"),
    getSpecialtyId("SELECT id FROM specialty WHERE specialty_name = ?"),
    updateSkill("UPDATE skill SET skill_name = ? WHERE id = ?"),
    updateSpecialty("UPDATE specialty SET specialty_name = ? WHERE id = ?"),
    deleteDeveloperById("UPDATE developer SET status = 'DELETED' WHERE id = ?"),
    deleteSkill("DELETE FROM skill WHERE id = ?"),
    deleteSpecialty("DELETE FROM specialty WHERE id = ?");

    private final String value;

    SqlQuery(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
