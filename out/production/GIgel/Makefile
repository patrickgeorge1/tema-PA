JFLAGS = -g
JC = javac

.SUFFIXES: .java .class


build:
	javac -g trezorerie/Bani.java


run-p1:
	java trezorerie.Bani


default: classes

classes: $(CLASSES:.java=.class)


clean:
	$(RM) trezorerie/*.class

