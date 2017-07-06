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
package com.mebigfatguy.neuron;

import com.mebigfatguy.neuron.internal.NeuralNet;
import com.mebigfatguy.neuron.internal.ToString;
import com.mebigfatguy.neuron.internal.branes.HiddenBrane;
import com.mebigfatguy.neuron.internal.branes.InputBrane;
import com.mebigfatguy.neuron.internal.branes.OutputBrane;

public class NeuronBuilder {

    private NeuralNet nn;

    public NeuronBuilder() {
        nn = new NeuralNet();
    }

    public NeuronBuilder withInputs(int inputs) {
        nn.setInputBrane(new InputBrane(inputs));
        return this;
    }

    public NeuronBuilder withOutputs(int outputs) {
        nn.setOutputBrane(new OutputBrane(outputs));
        return this;
    }

    public NeuronBuilder withHiddenBranes(int numHiddenLayers, int neurons) {
        for (int n = 0; n < numHiddenLayers; n++) {
            nn.addHiddenBrane(new HiddenBrane(neurons));
        }
        return this;
    }

    public NeuronBuilder withHiddenBrane(int neurons) {
        nn.addHiddenBrane(new HiddenBrane(neurons));
        return this;
    }

    public NeuronBuilder withActivation(Activation activation) {
        nn.applyActivation(activation);
        return this;
    }

    public NeuralNet build() {
        nn.validate();
        return nn;
    }

    @Override
    public String toString() {
        return ToString.build(this);
    }
}
