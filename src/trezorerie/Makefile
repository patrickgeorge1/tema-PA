JFLAGS = -g
JC = javac

.SUFFIXES: .java .class


build:
	javac -g Bani.java


run-p1:
	java Bani


default: classes

classes: $(CLASSES:.java=.class)


clean:
	$(RM) trezorerie/*.class

