package es.programacinoextrema.tensorflow;

import org.tensorflow.TensorFlow;

public class RNN1 {
	public RNN1(){
		
	}
	public void iniciar() {
		final String value = "Hello from " + TensorFlow.version();
		System.out.println(value);

	}
}
