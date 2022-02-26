package gov.iti.jets.service.impl;

import gov.iti.jets.common.dtos.MessageAnnounceDto;
import gov.iti.jets.common.interfaces.ClientAnnounceMessageInt;
import gov.iti.jets.common.interfaces.ServerMessageAnnouncetInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.services.LoginService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ClientAnnounceImpl extends UnicastRemoteObject implements ClientAnnounceMessageInt {


    RMIRegister rmiRegister=RMIRegister.getInstance();
    ServerMessageAnnouncetInt serverMessageAnnouncetInt;
    public static List<String> messagesFromAdmin=new ArrayList<>();

    public ClientAnnounceImpl() throws RemoteException {
        super();
       serverMessageAnnouncetInt=rmiRegister.serverMessageAnnouncetInt();
        System.out.println("registerd : "+serverMessageAnnouncetInt.register(this));
    }

    @Override
    public void reciveMessage(MessageAnnounceDto messageAnnounceDto) throws RemoteException {
//        System.out.println(messageAnnounceDto.toString());
        messagesFromAdmin.add(messageAnnounceDto.getMessageContent());
        for (String message:messagesFromAdmin ) {
            System.out.println("message="+message);
        }

    }
}
