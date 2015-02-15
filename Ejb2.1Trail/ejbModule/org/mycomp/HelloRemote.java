package org.mycomp;

import javax.ejb.*;
import javax.rmi.*;

public interface HelloRemote extends EJBObject{
	public String sayHello()throws RemoteException;
}
