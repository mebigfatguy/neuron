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
package com.mebigfatguy.neuron;

import org.junit.Assert;
import org.junit.Test;

import com.mebigfatguy.neuron.activations.LinearActivation;

public class NeuronBuilderTest {

    @Test
    public void testBuilder() {
        NeuronBuilder builder = new NeuronBuilder();

        NeuralNet nn = builder.withInputs(10).withHiddenBranes(3, 15).withOutputs(1).withActivation(new LinearActivation()).build();

        Assert.assertNotNull(nn);
        Assert.assertEquals(10, nn.getNumInputs());
        Assert.assertEquals(1, nn.getNumOutputs());
    }
}
