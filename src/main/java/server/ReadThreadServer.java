package server;

import util.LoginDTO;
import util.NetworkIO;

import java.io.IOException;
import java.util.HashMap;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkIO networkUtil;
    public HashMap<String, String> userMap;


    public ReadThreadServer(HashMap<String, String> map, NetworkIO networkUtil) {
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        networkUtil.write(loginDTO);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



