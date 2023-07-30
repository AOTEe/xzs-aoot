<template>
  <div class="space-right">
    <div class="space-right-top">
      <div class="title">
        <div><span>我的消息</span></div>
      </div>
      <div data-no-clip="no-clip" class="space-right-bottom ps">
        <div class="router-view">
          <div class="d">
            <div class="bili-im">
              <div class="im-popup" id="protect-pop-warning"
                   style="display: none;">
                <div
                  class="bp-popup-panel p-relative a-move-in-top a-forwards im-popup-shell"
                  style="width: 500px; display: none;">
                  <div class="title-ctnr p-relative"><h2 d class="popup-title">
                    接收异常提醒</h2>
                  </div>
                  <div class="popup-content-ctnr">
                    <div class="content">
                      <div class="popup-container"><p class="msg-main"></p>
                        <div class="config-item"></div>
                      </div>
                    </div>
                    <div class="popup-btn-ctnr t-center">
                      <button disabled="disabled"
                              class="bl-button panel-btn bl-button--primary bl-button--size"><span
                        class="txt">提交</span>
                      </button>
                      <button
                        class="bl-button panel-btn bl-button--ghost bl-button--size"><span
                        class="txt">取消</span>
                      </button>
                    </div>
                  </div>
                  <div role="button" title="关闭面板"
                       class="close-btn p-absolute bg-center bg-no-repeat pointer t-center"><i
                    class="bp-icon-font icon-close"></i>
                  </div>
                </div>
              </div>
              <div class="im-popup" id="protect-pop-setting"
                   style="display: none;">
                <div
                  class="bp-popup-panel p-relative a-move-in-top a-forwards im-popup-shell"
                  style="width: 500px; display: none;">
                  <div class="title-ctnr p-relative"><h2 class="popup-title">
                    设置成功</h2>
                  </div>
                  <div class="popup-content-ctnr">
                    <div class="content">
                      <div class="popup-container"><p>私信接收范围：</p>
                        <p>有效期：到截止</p>
                        <p class="msg-main">说明：有效期内可前往消息设置进行修改</p>
                        <span class="checkbox-container">
                          <span class="check-box-style"></span>
                          <input type="checkbox"
                                 id="clearHistoryBtn"
                                 value="false"><label
                          for="clearHistoryBtn">清除当前列表内不符合条件的会话</label>
                        </span>
                      </div>
                    </div>
                    <div class="popup-btn-ctnr t-center">
                      <button
                        class="bl-button panel-btn bl-button--primary bl-button--size">
                        <span class="txt">确定</span>
                      </button>
                    </div>
                  </div>
                  <div role="button" title="关闭面板"
                       class="close-btn p-absolute bg-center bg-no-repeat pointer t-center"><i
                    class="bp-icon-font icon-close"></i>
                  </div>
                </div>
              </div>
              <div class="console-message"></div>
              <whisperList
                :currentUser = "currentUser"
              ></whisperList>
              <whisperWindow
                :currentUser = "currentUser"
              >
              </whisperWindow>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import whisperList from '../message-center/components/whisper-list'
import whisperWindow from '../message-center/components/whisper-window'
import userApi from '@/api/user'
export default {
  components:{
    whisperList,
    whisperWindow
  },
  data() {
    // 聊天列表
    // 历史消息
    // 发送框数据
    return {
      currentUser : null

    }
  },
  methods:{

  },
  created () {
    let _this = this
    //mounted和methods的生命周期
    userApi.getCurrentUser().then(re => {
      _this.currentUser = re.response
      console.log(re.response)
    })
    console.log(this.currentUser)
  },
  mounted(){

  }
}
</script>

<style>
@import "css/message.css";
</style>
