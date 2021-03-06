package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.MessageAnnounceDto;
import gov.iti.jets.common.interfaces.ClientAnnounceMessageInt;
import gov.iti.jets.common.interfaces.ServerMessageAnnouncetInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerMessageAnnounceImpl extends UnicastRemoteObject implements ServerMessageAnnouncetInt {

    private final List<ClientAnnounceMessageInt> clientAnnounceMessageIntList = new ArrayList<>();
    private final static List<String> listMessageAnnounceDto= new ArrayList<>();


    public ServerMessageAnnounceImpl() throws RemoteException {
        super();

    }

    public  void getMessageAnnounceDto(MessageAnnounceDto messageAnnounceDto){
        System.out.println("messageDto in get yarab "+messageAnnounceDto.getMessageContent());
        listMessageAnnounceDto.add(messageAnnounceDto.getMessageContent());
        for(String messageAnnounceDto1 :listMessageAnnounceDto){
            System.out.println("messageDto in get"+messageAnnounceDto1);
        }
    }

    @Override
    public boolean getMessage() throws RemoteException {
        if(listMessageAnnounceDto.isEmpty()){
            return false;
        }else{
            sendMessage(listMessageAnnounceDto);
            return true;
        }
    }

    @Override
    public boolean register(ClientAnnounceMessageInt clientAnnounceMessageInt) throws RemoteException {
        if(!clientAnnounceMessageIntList.contains(clientAnnounceMessageInt)){
            return clientAnnounceMessageIntList.add(clientAnnounceMessageInt);
        }
        return false;
    }
    @Override
    public boolean unRegister(ClientAnnounceMessageInt clientAnnounceMessageInt) throws RemoteException {
        if (clientAnnounceMessageIntList.contains(clientAnnounceMessageInt)) {
            return clientAnnounceMessageIntList.remove(clientAnnounceMessageInt);
        }return false;
    }

    @Override
    public void removeList() throws RemoteException {
        listMessageAnnounceDto.clear();
    }

    public boolean sendMessage(List<String> messageAnnounceDto){
                System.out.println(clientAnnounceMessageIntList.size());
            for(ClientAnnounceMessageInt client : clientAnnounceMessageIntList){
                try {
                    client.reciveMessage(messageAnnounceDto);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        return true ;
    }

}
