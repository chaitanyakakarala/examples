package org.mycomp;

import javax.ejb.*;
import javax.rmi.*;

public interface HelloHome extends EJBHome{
	public HelloRemote create()throws RemoteException, CreateException;
}
