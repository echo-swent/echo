package com.github.swent.echo.classes_tests

import org.junit.Assert.*
import org.junit.Test
import com.github.swent.echo.classes.Association

class AssociationTest {
    @Test
    fun testUser_Profile() {
        // Arrange
        val expectedAssociationId  = "testId"
        var expectedName  = "testName"
        var expectedDescription  = "testDescription"

        // Act
        val association = Association(expectedAssociationId, expectedName, expectedDescription)

        // Assert
        assertEquals(expectedAssociationId, association.associationId)
        assertEquals(expectedName, association.name)
        assertEquals(expectedDescription, association.description)
    }
}