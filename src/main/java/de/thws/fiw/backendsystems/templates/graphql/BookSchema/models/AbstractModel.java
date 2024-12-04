package de.thws.fiw.backendsystems.templates.graphql.BookSchema.models;

public abstract class AbstractModel {
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
