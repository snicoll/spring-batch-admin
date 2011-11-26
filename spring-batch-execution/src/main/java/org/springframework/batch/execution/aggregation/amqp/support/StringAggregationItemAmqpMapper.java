/*
 * Copyright 2006-2007 the original author or authors.
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
package org.springframework.batch.execution.aggregation.amqp.support;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.batch.execution.aggregation.amqp.AbstractAggregationItemAmqpMapper;

import java.io.IOException;

/**
 * Maps the body of a {@link Message} to a simple <tt>String</tt>.
 *
 * @author Stephane Nicoll
 */
public class StringAggregationItemAmqpMapper extends AbstractAggregationItemAmqpMapper<String> {

    private final MessageConverter messageConverter = new SimpleMessageConverter();

    @Override
    protected String doMap(Message message) throws IOException {
        final Object o = messageConverter.fromMessage(message);
        if (o instanceof String) {
            return (String) o;
        } else {
            throw new IllegalArgumentException("Expected String message but got [" + o + "]");
        }
    }
}
