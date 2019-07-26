package core.event.handler;

import core.event.IEvent;
import core.event.subscriber.IEventSubscriber;

import java.util.Collection;

public interface IEventHandler<T extends IEvent> {

    Collection<IEventSubscriber> getSubscriptions();
    void handle(T e);
}
