/*
 * neuron - a neural network
 * Copyright (C) 2017-2019 Dave Brosius
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations
 * under the License.
 */
package com.mebigfatguy.neuron.internal;

import java.security.SecureRandom;

public class Neuron {

    private double[] weights;
    private double bias;

    public Neuron(int numberOfInputs) {

        weights = new double[numberOfInputs];
    }

    public void init(SecureRandom r) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = r.nextDouble();
        }

        bias = r.nextDouble();
    }

    public double process(double[] input) {
        return 0;
    }

    @Override
    public String toString() {
        return ToString.build(this);
    }

}
