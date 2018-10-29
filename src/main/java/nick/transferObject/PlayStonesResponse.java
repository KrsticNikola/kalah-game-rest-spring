package nick.transferObject;

import java.util.HashMap;

/**
 * Created by Nikola on 9/16/2018.
 */
public class PlayStonesResponse {

    private String id;
    private String url;
    private HashMap status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap getStatus() {
        return status;
    }

    public void setStatus(HashMap status) {
        this.status = status;
    }
}
