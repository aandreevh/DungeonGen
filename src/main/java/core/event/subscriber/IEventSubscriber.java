package core.event.subscriber;

import core.event.IEvent;
import core.event.handler.IEventHandler;

import java.util.Collection;

public interface IEventSubscriber<T extends IEvent> extends IEventHandler<T> {

    boolean subscribe(IEventHandler<? extends T> handler,int priority);
    boolean unsubscribe(IEventHandler<? extends T> handler);

    Collection<IEventHandler> getHandlers();


}
