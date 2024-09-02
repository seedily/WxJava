package com.binarywang.solon.wxjava.cp.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import java.io.Serializable;

/**
 * 企业微信接入相关配置属性
 *
 * @author yl
 * created on  2021/12/6
 */
@Data
@NoArgsConstructor
@Configuration
@Inject("${" + WxCpProperties.PREFIX + "}")
public class WxCpProperties {
  public static final String PREFIX = "wx.cp";

  /**
   * 微信企业号 corpId
   */
  private String corpId;
  /**
   * 微信企业号 corpSecret
   */
  private String corpSecret;
  /**
   * 微信企业号应用 token
   */
  private String token;
  /**
   * 微信企业号应用 ID
   */
  private Integer agentId;
  /**
   * 微信企业号应用 EncodingAESKey
   */
  private String aesKey;
  /**
   * 微信企业号应用 会话存档私钥
   */
  private String msgAuditPriKey;
  /**
   * 微信企业号应用 会话存档类库路径
   */
  private String msgAuditLibPath;

  /**
   * 配置存储策略，默认内存
   */
  private ConfigStorage configStorage = new ConfigStorage();

  @Data
  @NoArgsConstructor
  public static class ConfigStorage implements Serializable {
    private static final long serialVersionUID = 4815731027000065434L;
    /**
     * 存储类型
     */
    private StorageType type = StorageType.memory;

    /**
     * 指定key前缀
     */
    private String keyPrefix = "wx:cp";

    /**
     * redis连接配置
     */
    private WxCpRedisProperties redis = new WxCpRedisProperties();

    /**
     * http代理主机
     */
    private String httpProxyHost;

    /**
     * http代理端口
     */
    private Integer httpProxyPort;

    /**
     * http代理用户名
     */
    private String httpProxyUsername;

    /**
     * http代理密码
     */
    private String httpProxyPassword;

    /**
     * http 请求最大重试次数
     * <pre>
     *   {@link me.chanjar.weixin.cp.api.WxCpService#setMaxRetryTimes(int)}
     *   {@link me.chanjar.weixin.cp.api.impl.BaseWxCpServiceImpl#setMaxRetryTimes(int)}
     * </pre>
     */
    private int maxRetryTimes = 5;

    /**
     * http 请求重试间隔
     * <pre>
     *   {@link me.chanjar.weixin.cp.api.WxCpService#setRetrySleepMillis(int)}
     *   {@link me.chanjar.weixin.cp.api.impl.BaseWxCpServiceImpl#setRetrySleepMillis(int)}
     * </pre>
     */
    private int retrySleepMillis = 1000;
  }

  public enum StorageType {
    /**
     * 内存
     */
    memory,
    /**
     * jedis
     */
    jedis,
    /**
     * redisson
     */
    redisson,
    /**
     * redistemplate
     */
    redistemplate
  }
}
