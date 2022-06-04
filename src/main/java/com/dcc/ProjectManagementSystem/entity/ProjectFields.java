package com.dcc.ProjectManagementSystem.entity;

public class ProjectFields {
    private Integer table_num;
    private String table_fields;
    private String table_tag;
    private Integer fields_update;
    private Integer fields_select;
    private Integer fields_delete;
    private Integer table_id;

    public Integer getTable_num() {
        return table_num;
    }

    public Integer getFields_delete() {
        return fields_delete;
    }

    public void setFields_delete(Integer fields_delete) {
        this.fields_delete = fields_delete;
    }

    public void setTable_num(Integer table_num) {
        this.table_num = table_num;
    }

    public String getTable_fields() {
        return table_fields;
    }

    public void setTable_fields(String table_fields) {
        this.table_fields = table_fields;
    }

    public String getTable_tag() {
        return table_tag;
    }

    public void setTable_tag(String table_tag) {
        this.table_tag = table_tag;
    }

    public Integer getFields_update() {
        return fields_update;
    }

    public void setFields_update(Integer fields_update) {
        this.fields_update = fields_update;
    }

    public Integer getFields_select() {
        return fields_select;
    }

    public void setFields_select(Integer fields_select) {
        this.fields_select = fields_select;
    }

    public Integer getTable_id() {
        return table_id;
    }

    public void setTable_id(Integer table_id) {
        this.table_id = table_id;
    }
}
