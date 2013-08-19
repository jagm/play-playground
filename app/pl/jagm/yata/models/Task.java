package pl.jagm.yata.models;

import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import uk.co.panaxiom.playjongo.PlayJongo;

import static org.jongo.Oid.withOid;

public class Task {

    public static MongoCollection tasks() {
        return PlayJongo.getCollection("tasks");
    }

    @Id @ObjectId
    private String id;

    private String name;

    public Task insert() {
        tasks().save(this);
        return this;
    }

    public void remove() {
        tasks().remove(this.id);
    }

    public static Task findById(String id) {
        return tasks().findOne(withOid(id)).as(Task.class);
    }

    @Override
    public String toString() {
        return String.format("[#%s] %s", id, name);
    }

    public String getId() {
        return id;
    }

    public Task setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

}
