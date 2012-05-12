package com.tinkerpop.blueprints.pgm.util.wrappers.id;

import com.tinkerpop.blueprints.pgm.Element;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Joshua Shinavier (http://fortytwo.net)
 */
public abstract class IdElement implements Element {
    protected final Element baseElement;

    public IdElement(final Element baseElement) {
        this.baseElement = baseElement;
    }

    public Object getProperty(final String key) {
        if (key.equals(IdGraph.ID)) {
            return null;
        }

        return baseElement.getProperty(key);
    }

    public Set<String> getPropertyKeys() {
        Set<String> keys = baseElement.getPropertyKeys();

        // TODO: creating a new set for each call is expensive...
        // but so is maintaining a second set or doing comparisons on each read from the set
        Set<String> s = new HashSet<String>();
        s.addAll(keys);
        s.remove(IdGraph.ID);

        return s;
    }

    public void setProperty(final String key, final Object value) {
        if (key.equals(IdGraph.ID)) {
            throw new IllegalArgumentException("Unable to set value for reserved property " + IdGraph.ID);
        }

        baseElement.setProperty(key, value);
    }

    public Object removeProperty(final String key) {
        if (key.equals(IdGraph.ID)) {
            throw new IllegalArgumentException("Unable to remove value for reserved property " + IdGraph.ID);
        }

        return baseElement.removeProperty(key);
    }

    public Object getId() {
        return baseElement.getProperty(IdGraph.ID);
    }
}