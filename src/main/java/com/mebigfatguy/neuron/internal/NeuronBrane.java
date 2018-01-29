/*
 * neuron - a neural network
 * Copyright (C) 2017-2018 Dave Brosius
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
import java.util.List;

import com.mebigfatguy.neuron.Activation;
import com.mebigfatguy.neuron.activations.LinearActivation;

public interface NeuronBrane {

    default void init(SecureRandom r) {
        for (Neuron n : getNeurons()) {
            n.init(r);
        }
        if (getActivation() == null) {
            setActivation(new LinearActivation());
        }
    }

    default double[] process(double[] input) {
        List<Neuron> neurons = getNeurons();
        double[] output = new double[neurons.size()];

        int i = 0;
        for (Neuron neuron : neurons) {
            output[i++] = neuron.process(input);
        }

        return output;
    }

    List<Neuron> getNeurons();

    Activation getActivation();

    void setActivation(Activation activation);
}
