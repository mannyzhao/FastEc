package com.zhaoman.fastec.generator;


import com.zhaoman.manny_annotations.PayEntryGenerator;
import com.zhaoman.manny_core.wechat.temepletes.WXPayEntryTemplate;

/**
 * Author:zhaoman
 * Date:2018/11/12
 * Description:
 */
@PayEntryGenerator(
        packageName = "com.zhaoman.fastec",
        payEntryTemplate=WXPayEntryTemplate.class
)
public class WeChatPayEntry {
}
