package rsoi.lab5.seservice.entity.oauth;

import rsoi.lab5.seservice.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "oauth_token")
public class OAuthToken extends BaseEntity {

    @Column(name = "access_token", length = 1000, nullable = false)
    private String accessToken;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Column(name = "refresh_token", length = 1000, nullable = false)
    private String refreshToken;

    @Column(name = "access_token_validity", nullable = false)
    private boolean accessTokenValidity;

    @Column(name = "refresh_token_validity", nullable = false)
    private boolean refreshTokenValidity;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(boolean accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public boolean isRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(boolean refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }
}
