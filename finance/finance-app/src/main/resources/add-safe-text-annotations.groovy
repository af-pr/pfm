import org.yaml.snakeyaml.Yaml
import java.nio.file.Files
import java.nio.file.Paths

// Constants
final String SAFE_TEXT_CLASS = 'com.afpr.pfm.finance.validation.SafeText'
final String SAFE_TEXT_IMPORT = "import ${SAFE_TEXT_CLASS};"
final String SAFE_TEXT_ANNOTATION = '@SafeText'
final String PACKAGE_DECL = 'package com.afpr.pfm.finance.client.dto;'
final String FIELD_INDENT = '  '

// Paths
final String openApiPath = project.basedir.absolutePath + '/src/main/resources/openapi.yaml'
final String dtoPath = project.build.directory + '/generated-sources/openapi/src/main/java/com/afpr/pfm/finance/client/dto'

// Parse YAML
def yaml = new Yaml()
def spec = yaml.load(Files.newInputStream(Paths.get(openApiPath)))

// Find all DTOs with x-safeText fields
spec.components.schemas.each { schemaName, schema ->
    schema.properties?.each { propName, propDef ->
        if (propDef['x-safeText']) {
            def javaFile = new File(dtoPath, "${schemaName}.java")
            if (javaFile.exists()) {
                def content = javaFile.text

                // Add import if missing
                if (!content.contains(SAFE_TEXT_IMPORT)) {
                    content = content.replace(PACKAGE_DECL, "${PACKAGE_DECL}\n\n${SAFE_TEXT_IMPORT}")
                }

                // Annotate the field declaration
                def fieldDecl = "${FIELD_INDENT}private String ${propName};"
                def annotatedField = "${FIELD_INDENT}${SAFE_TEXT_ANNOTATION}\n${fieldDecl}"
                if (content.contains(fieldDecl) && !content.contains(annotatedField)) {
                    content = content.replace(fieldDecl, annotatedField)
                }

                javaFile.text = content
            }
        }
    }
}

