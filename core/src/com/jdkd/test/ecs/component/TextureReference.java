package com.jdkd.test.ecs.component;

import com.artemis.Component;

public class TextureReference extends Component {

    private String reference;

    public TextureReference(String reference) {
        this.reference = reference;
    }

    public TextureReference() {
    }

    public String getReference() {
        return reference;
    }
}
