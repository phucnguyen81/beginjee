package lou.beginjee.jpa;

import java.util.HashMap;

import javax.persistence.Persistence;

import lou.beginjee.util.C;

/**
 * Generates the create-and-drop scripts.
 */
public class GenerateSchema {

    public static void main(String[] args) {
        HashMap<String, String> props = new HashMap<>();
        props.put("javax.persistence.schema-generation.scripts.action",
            "drop-and-create");
        props.put("javax.persistence.schema-generation.scripts.drop-target",
            "target/drop-target.ddl");
        props.put("javax.persistence.schema-generation.scripts.create-target",
            "target/create-target.ddl");
        Persistence.generateSchema(C.PU, props);
    }

}