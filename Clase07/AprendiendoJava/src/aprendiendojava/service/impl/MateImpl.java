package aprendiendojava.service.impl;

import aprendiendojava.service.spec.MateSpec;

public class MateImpl implements MateSpec {

	@Override
	public int sumar(int n1, int n2) {
		return (n1 + n2);
	}

	@Override
	public int restar(int n1, int n2) {
		return (n1 - n2);
	}

}
