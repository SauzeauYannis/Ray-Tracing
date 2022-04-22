JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	raytracing/Main.java \
	raytracing/object/Checkerboard.java \
	raytracing/object/IntersectableObject.java \
	raytracing/object/Plane.java \
	raytracing/object/Sphere.java \
	raytracing/scene/Light.java \
	raytracing/scene/Scene.java \
	raytracing/util/Color.java \
	raytracing/util/JavaTga.java \
	raytracing/util/Vec3.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) raytracing/*.class
	$(RM) raytracing/*/*.class
