package me.huzorro.simple.enumt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import me.huzorro.simple.LoginRequest;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public interface WrapStructureDetail {
    enum RequestMap implements WrapStructureDetail {
        SubmitRequestMap(new ConcurrentHashMap<String, Object>());
        private Map<String, Object> map;
        private RequestMap(Map<String, Object> map) {
            this.map = map;
        }
        public Map<String, Object> getMap() {
            return this.map;
        }
    }
    enum LoginResponse implements WrapStructureDetail {
        
    }
    
}
