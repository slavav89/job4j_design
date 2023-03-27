package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void whenVertex2ThenUnknownObject() {
        Box box = new Box(2, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .contains("object")
                .isNotEmpty()
                .isNotNull();
    }

    @Test
    void whenEdge0ThenUnknownObjects() {
        Box box = new Box(4, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .contains("object")
                .endsWith("ect")
                .isNotNull();
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .startsWith("Tet")
                .isNotEmpty();
    }

    @Test
    void isVertexFour() {
        Box box = new Box(4, 4);
        int angle = box.getNumberOfVertices();
        assertThat(angle).isEqualTo(4)
                .isPositive()
                .isGreaterThan(3);
    }

    @Test
    void isVertexNegative() {
        Box box = new Box(4, 0);
        int angle = box.getNumberOfVertices();
        assertThat(angle).isEqualTo(-1)
                .isNegative()
                .isLessThan(0);
    }

    @Test
    void isTrue() {
        Box box = new Box(4, 4);
        boolean bool = box.isExist();
        assertThat(bool).isTrue();
    }

    @Test
    void isFalse() {
        Box box = new Box(4, 0);
        boolean bool = box.isExist();
        assertThat(bool).isFalse();
    }

    @Test
    void whenVertex4ThenArea27point71() {
        Box box = new Box(4, 4);
        double area = box.getArea();
        assertThat(area).isEqualTo(27.71d, withPrecision(0.01d))
                .isCloseTo(27.71d, withPrecision(0.01d))
                .isGreaterThan(27.71d)
                .isLessThan(27.72d);
    }

    @Test
    void whenVertex2ThenArea0() {
        Box box = new Box(2, 4);
        double area = box.getArea();
        assertThat(area).isEqualTo(0)
                .isGreaterThan(-1)
                .isLessThan(1);
    }
}