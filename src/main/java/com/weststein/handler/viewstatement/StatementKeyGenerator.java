package com.weststein.handler.viewstatement;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
public class StatementKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return new StatementKey((String) params[0], (LocalDateTime) params[1], (LocalDateTime) params[2]);
    }

    public class StatementKey implements Serializable {

        private final int hashCode;
        private final String id;
        private final LocalDateTime from;
        private final LocalDateTime to;

        public StatementKey(String id, LocalDateTime from, LocalDateTime to) {
            Assert.notNull(id, "id must not be null");
            Assert.notNull(from, "from date must not be null");
            Assert.notNull(to, "to date must not be null");
            this.id = id;
            this.from = from;
            this.to = to;
            this.hashCode = Integer.valueOf(id);
        }

        // we want to make sure that requested statement is within already cached statement
        @Override
        public boolean equals(Object obj) {
            return (this == obj || (obj instanceof StatementKey
                    && id.equals(((StatementKey) obj).id) && !((StatementKey) obj).from.isAfter(from) && !((StatementKey) obj).to.isAfter(to)));
        }

        @Override
        public final int hashCode() {
            return this.hashCode;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " [" + id + ", from: " + from + ", to: " + to + "]";
        }
    }
}