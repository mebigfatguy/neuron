/*
 * neuron - a neural network
 * Copyright (C) 2017 Dave Brosius
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
package com.mebigfatguy.neuron.internal.branes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mebigfatguy.neuron.Activation;
import com.mebigfatguy.neuron.internal.Neuron;
import com.mebigfatguy.neuron.internal.NeuronBrane;
import com.mebigfatguy.neuron.internal.ToString;

public class HiddenBrane implements NeuronBrane {
    private List<Neuron> neurons;
    private Activation activation;

    public HiddenBrane(int numberOfInputs) {

        neurons = new ArrayList<>(numberOfInputs);
        for (int n = 0; n < numberOfInputs; n++) {
            neurons.add(new Neuron(numberOfInputs));
        }
        neurons = Collections.unmodifiableList(neurons);
    }

    @Override
    public List<Neuron> getNeurons() {
        return neurons;
    }

    @Override
    public Activation getActivation() {
        return activation;
    }

    @Override
    public void setActivation(Activation activationFunction) {
        activation = activationFunction;
    }

    @Override
    public String toString() {
        return ToString.build(this);
    }
}
