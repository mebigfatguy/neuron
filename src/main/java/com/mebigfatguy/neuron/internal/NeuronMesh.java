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
import java.util.ArrayList;
import java.util.List;

import com.mebigfatguy.neuron.Activation;
import com.mebigfatguy.neuron.NeuralNet;
import com.mebigfatguy.neuron.internal.branes.HiddenBrane;
import com.mebigfatguy.neuron.internal.branes.InputBrane;
import com.mebigfatguy.neuron.internal.branes.OutputBrane;

public class NeuronMesh implements NeuralNet {

    private InputBrane inputBrane;
    private List<NeuronBrane> branes;
    private OutputBrane outputBrane;

    public NeuronMesh() {
        branes = new ArrayList<>();
    }

    public void setInputBrane(InputBrane brane) {
        inputBrane = brane;
    }

    public void setOutputBrane(OutputBrane brane) {
        outputBrane = brane;
    }

    @Override
    public double[] process(double[] inputs) {
        double[] output = inputBrane.process(inputs);
        for (NeuronBrane brane : branes) {
            output = brane.process(output);
        }
        return outputBrane.process(output);
    }

    public void addHiddenBrane(HiddenBrane brane) {
        branes.add(brane);
    }

    public void applyActivation(Activation activation) {
        if ((inputBrane != null) && (inputBrane.getActivation() == null)) {
            inputBrane.setActivation(activation);
        }

        for (NeuronBrane brane : branes) {
            if (((HiddenBrane) brane).getActivation() == null) {
                ((HiddenBrane) brane).setActivation(activation);
            }
        }
        if ((outputBrane != null) && (outputBrane.getActivation() == null)) {
            outputBrane.setActivation(activation);
        }
    }

    public void init() {
        SecureRandom r = new SecureRandom();
        inputBrane.init(r);
        for (NeuronBrane brane : branes) {
            brane.init(r);
        }
        outputBrane.init(r);
    }

    @Override
    public int getNumInputs() {
        if (inputBrane == null) {
            return 0;
        }

        return inputBrane.getNeurons().size();
    }

    @Override
    public int getNumOutputs() {
        return outputBrane.getNeurons().size();
    }

    @Override
    public String toString() {
        return ToString.build(this);
    }
}
