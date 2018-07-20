/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.core.base.accumulators;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * An implementation of an accumulator capable of calculating maximum values
 */
public class NumericMinAccumulateFunction extends AbstractAccumulateFunction<NumericMinAccumulateFunction.MaxData> {

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }

    public void writeExternal(ObjectOutput out) throws IOException {

    }

    protected static class MaxData implements Externalizable {
        public Number min = null;

        public MaxData() {}

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            min = (Double) in.readObject();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(min);
        }

        @Override
        public String toString() {
            return "min";
        }
    }

    public MaxData createContext() {
        return new MaxData();
    }

    public void init(MaxData data) {
        data.min = null;
    }

    public void accumulate(MaxData data,
                           Object value) {
        if (value != null) {
            Number number = (Number)value;
            data.min = data.min == null || data.min.doubleValue() > number.doubleValue() ? number : data.min;
        }
    }

    public void reverse(MaxData data,
                        Object value) {
    }

    public Object getResult(MaxData data) {
        return data.min;
    }

    public boolean supportsReverse() {
        return false;
    }

    public Class<?> getResultType() {
        return Number.class;
    }
}