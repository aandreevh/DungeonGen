package core.component;

import core.component.event.IComponentEvent;
import core.event.handler.IEventHandler;

import java.util.Collection;

public interface IComponent extends IEventHandler<IComponentEvent> {

    Collection<IComponent> getComponents();
    Collection<IComponent> getComponents(Class<? extends IComponent> c);
}
