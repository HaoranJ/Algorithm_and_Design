//try-final block guarantees execution of termination method
Foo foo = new Foo(...);
try {
  //do something
} finally {
  foo.terminate(); //explicit termination method
}

//Manual finalizer chaining
@Override protected void finalize() throws Throwable {
  try {

  } finally {
    super.finalize();
  }
}

//Finalizer Guardian idiom
public class Foo {
  //Sole purpose of this object is to finalize outer Foo Object
  private final Object finalizerGuardian = new Object() {
    @Override protected void finalize() throws Throwable {
      ...// Finalize outer Foo object
    }
  }
}
