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
package com.mebigfatguy.neuron.activations;

import com.mebigfatguy.neuron.Activation;
import com.mebigfatguy.neuron.internal.ToString;

public class HyperTanActivation implements Activation {
    double lowValue;
    double range;

    public HyperTanActivation() {
        lowValue = hyperTan(0.0);
        range = hyperTan(1.0) - lowValue;
    }

    @Override
    public double adjust(double input) {
        return (hyperTan(input) - lowValue) / range;
    }

    private double hyperTan(double input) {
        return ((2.0 / (1 + Math.pow(Math.E, -2.0 * input)))) - 1.0;
    }

    @Override
    public String toString() {
        return ToString.build(this);
    }
}
