<template>
  <div class="right">
    <div class="dialog">
      <div class="title_mid">
        <div class="is-limit" style="display: none;">该用户已被封禁～</div>
        <div class="is-black" style="display: none;">(&gt;﹏&lt; )该用户已经被你加入黑名单</div>
        <span>{{whisperName}}</span>
        <div class="action-menu" style="top: 3px; right: 0px;">
          <div class="menu-list" style="display: none;"><!----><a
            class="btn">置顶聊天</a>
            <!----><!----><a class="btn">开启免打扰</a><!----><!----><a
              class="btn">加入黑名单</a>
            <!----><!----><a class="btn">举报该用户</a><!----><!----><!----><a
              class="btn"><span class="name">不接收推送</span><span
              class="tips">通知正常接收</span></a></div>
          <a class="btn bp-icon-font icon-more-1"></a></div>
      </div>
      <GeminiScrollbar  class="gm-scrollbar-window">
      <div class="message-list">

        <div class="message-list-content min_h_100">
          <div class="msg-more"><span
            class="load-more"
            style="display: none;"><span
            class="icon"></span>查看和他的历史私信消息</span><span
            class="no-more"
            style="">没有更多消息了～</span><span
            class="loading" style="display: none;"><div
            class="lds-spinner"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div></span><span
            class="error" style="display: none;">消息加载失败，<span
            class="btn">点击重新加载</span></span>
          </div>
          <div class="msg-time"><span class="time">2023年7月16日 10:09</span>
          </div>
          <div v-for="message in messageList" class="msg-item"
               :class="whisperId==message.senderId?'msg-item not-me':'msg-item is-me'">

            <a  href="//space.bilibili.com/555047479" target="_blank" class="avatar" :title="whisperName" ></a>

            <div class="message">
              <div  :class="whisperId==message.senderId?'message-content not-me':'message-content is-me'">
                {{message.msgContent}}
              </div></div>
          </div>
          <div class="msg-push-new" talkerid="555047479" style="display: none;"><a
            href="" target="_blank" class="ar-recommend-item"><img class="ar-recommend-item__img"
                                                                   data-src=""
                                                                   src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"
                                                                   lazy="loading">
            <div class="ar-recommend-item__info">
              <div class="ar-recommend-item__info--title"></div>
              <div class="ar-recommend-item__info--desc"><!---->
                <div class="time">1970-1-1</div>
              </div>
              <div class="ar-recommend-item__info--view"><i class="bp-icon-font icon-play-a"></i><span
                class="view">0</span><i class="bp-icon-font icon-danmu-a"></i><span class="chat">0</span>
              </div>
            </div>
          </a></div>
        </div>

      </div>
      </GeminiScrollbar>
      <div class="new-message-tip" style="display: none;">
        <div class="text">您有 0 条新消息</div>
      </div>
      <div class="send-box">
        <div class="row">
          <div class="space-margin"><label
            class="image-upload-btn"></label></div>
          <div class="space-margin emoji-container">
            <button title="表情" class="emotion-btn-box"></button><!----></div><!---->
        </div>
        <div placeholder="回复一下吧～" class="input-box">
          <textarea id="editor" class="core-style" contenteditable="true"
                    v-model="textContent"
               style="height: 60px; outline: none; resize: none">
          </textarea>
          <div class="indicator" style="bottom: -30px; right: 100px;"><span
            class="">0</span>/<span>500</span></div>
        </div>
        <div class="row right">
          <button class="btn-box send-btn" @click="sendMsg" title="enter 发送
shift + enter 换行">发送
          </button>
        </div>
      </div>
      <div class="im-popup report-popup"
           style="display: none;">
        <div
          class="bp-popup-panel p-relative a-move-in-top a-forwards im-popup-shell"
          style="width: 500px; display: none;">
          <div class="title-ctnr p-relative"><h2 class="popup-title">
            举报该用户</h2></div>
          <div class="popup-content-ctnr">
            <div class="content">
              <div class="content-text">请选择您举报<span class="name">我是程序员小灰</span>的理由，该对话近期内的消息将作为证据一并提交。
              </div><!---->
              <div class="selector-box"><label
                class="type-selector"><input
                name="report-type" type="radio" class="radio" value="1">
                <svg viewBox="0 0 40 40" class="radio-icon css-o1815x">
                  <path class="content"
                        d="M20,0h0A20,20,0,0,1,40,20h0A20,20,0,0,1,20,40h0A20,20,0,0,1,0,20H0A20,20,0,0,1,20,0Z"></path>
                  <path class="border"
                        d="M2.5,20A17.5,17.5,0,1,0,20,2.5,17.51,17.51,0,0,0,2.5,20ZM0,20A20,20,0,1,1,20,40,20,20,0,0,1,0,20Z"></path>
                  <path class="dot"
                        d="M20,10h0A10,10,0,0,1,30,20h0A10,10,0,0,1,20,30h0A10,10,0,0,1,10,20h0A10,10,0,0,1,20,10Z"></path>
                </svg>
                色情低俗</label><label class="type-selector"><input
                name="report-type" type="radio" class="radio" value="2">
                <svg viewBox="0 0 40 40" class="radio-icon css-1xneiug">
                  <path class="content"
                        d="M20,0h0A20,20,0,0,1,40,20h0A20,20,0,0,1,20,40h0A20,20,0,0,1,0,20H0A20,20,0,0,1,20,0Z"></path>
                  <path class="border"
                        d="M2.5,20A17.5,17.5,0,1,0,20,2.5,17.51,17.51,0,0,0,2.5,20ZM0,20A20,20,0,1,1,20,40,20,20,0,0,1,0,20Z"></path>
                  <path class="dot"
                        d="M20,10h0A10,10,0,0,1,30,20h0A10,10,0,0,1,20,30h0A10,10,0,0,1,10,20h0A10,10,0,0,1,20,10Z"></path>
                </svg>
                政治敏感</label><label class="type-selector"><input
                name="report-type" type="radio" class="radio" value="3">
                <svg viewBox="0 0 40 40" class="radio-icon css-1xneiug">
                  <path class="content"
                        d="M20,0h0A20,20,0,0,1,40,20h0A20,20,0,0,1,20,40h0A20,20,0,0,1,0,20H0A20,20,0,0,1,20,0Z"></path>
                  <path class="border"
                        d="M2.5,20A17.5,17.5,0,1,0,20,2.5,17.51,17.51,0,0,0,2.5,20ZM0,20A20,20,0,1,1,20,40,20,20,0,0,1,0,20Z"></path>
                  <path class="dot"
                        d="M20,10h0A10,10,0,0,1,30,20h0A10,10,0,0,1,20,30h0A10,10,0,0,1,10,20h0A10,10,0,0,1,20,10Z"></path>
                </svg>
                违法有害</label><label class="type-selector"><input
                name="report-type" type="radio" class="radio" value="4">
                <svg viewBox="0 0 40 40" class="radio-icon css-1xneiug">
                  <path class="content"
                        d="M20,0h0A20,20,0,0,1,40,20h0A20,20,0,0,1,20,40h0A20,20,0,0,1,0,20H0A20,20,0,0,1,20,0Z"></path>
                  <path class="border"
                        d="M2.5,20A17.5,17.5,0,1,0,20,2.5,17.51,17.51,0,0,0,2.5,20ZM0,20A20,20,0,1,1,20,40,20,20,0,0,1,0,20Z"></path>
                  <path class="dot"
                        d="M20,10h0A10,10,0,0,1,30,20h0A10,10,0,0,1,20,30h0A10,10,0,0,1,10,20h0A10,10,0,0,1,20,10Z"></path>
                </svg>
                广告骚扰</label><label class="type-selector"><input
                name="report-type" type="radio" class="radio" value="5">
                <svg viewBox="0 0 40 40" class="radio-icon css-1xneiug">
                  <path class="content"
                        d="M20,0h0A20,20,0,0,1,40,20h0A20,20,0,0,1,20,40h0A20,20,0,0,1,0,20H0A20,20,0,0,1,20,0Z"></path>
                  <path class="border"
                        d="M2.5,20A17.5,17.5,0,1,0,20,2.5,17.51,17.51,0,0,0,2.5,20ZM0,20A20,20,0,1,1,20,40,20,20,0,0,1,0,20Z"></path>
                  <path class="dot"
                        d="M20,10h0A10,10,0,0,1,30,20h0A10,10,0,0,1,20,30h0A10,10,0,0,1,10,20h0A10,10,0,0,1,20,10Z"></path>
                </svg>
                人身攻击</label><label class="type-selector"><input
                name="report-type" type="radio" class="radio" value="6">
                <svg viewBox="0 0 40 40" class="radio-icon css-1xneiug">
                  <path class="content"
                        d="M20,0h0A20,20,0,0,1,40,20h0A20,20,0,0,1,20,40h0A20,20,0,0,1,0,20H0A20,20,0,0,1,20,0Z"></path>
                  <path class="border"
                        d="M2.5,20A17.5,17.5,0,1,0,20,2.5,17.51,17.51,0,0,0,2.5,20ZM0,20A20,20,0,1,1,20,40,20,20,0,0,1,0,20Z"></path>
                  <path class="dot"
                        d="M20,10h0A10,10,0,0,1,30,20h0A10,10,0,0,1,20,30h0A10,10,0,0,1,10,20h0A10,10,0,0,1,20,10Z"></path>
                </svg>
                诈骗</label><label
                class="type-selector other-reason"><input
                name="report-type" type="radio"
                class="radio" value="0">
                <svg viewBox="0 0 40 40" class="radio-icon css-1xneiug">
                  <path class="content"
                        d="M20,0h0A20,20,0,0,1,40,20h0A20,20,0,0,1,20,40h0A20,20,0,0,1,0,20H0A20,20,0,0,1,20,0Z"></path>
                  <path class="border"
                        d="M2.5,20A17.5,17.5,0,1,0,20,2.5,17.51,17.51,0,0,0,2.5,20ZM0,20A20,20,0,1,1,20,40,20,20,0,0,1,0,20Z"></path>
                  <path class="dot"
                        d="M20,10h0A10,10,0,0,1,30,20h0A10,10,0,0,1,20,30h0A10,10,0,0,1,10,20h0A10,10,0,0,1,20,10Z"></path>
                </svg>
                其他问题</label></div>
              <div class="report-tip">为帮助审核人员更快处理，请补充问题类型和出现位置等详细信息。</div>
              <div class="input-box report-input"><textarea
                placeholder="举报内容" maxlength="50" autofocus="autofocus" class="textarea"
                style="height: 86px;"></textarea>
                <div class="indicator"><span class="">0</span>/<span
                >50</span></div>
              </div>
            </div>
            <div class="popup-btn-ctnr t-center">
              <button
                class="bl-button panel-btn bl-button--primary bl-button--size"><span
                class="txt">确定</span></button>
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
        </div><!----></div>
      <div>
        <div class="im-popup confirm-popup" style="display: none;">
          <div
            class="bp-popup-panel p-relative a-move-in-top a-forwards im-popup-shell"
            style="width: 500px; display: none;">
            <div class="title-ctnr p-relative"><h2
              class="popup-title">黑名单</h2></div>
            <div class="popup-content-ctnr">
              <div class="content">
                <div class="content-text">加入黑名单后将不再收到该用户的新消息，并自动解除关注关系。</div>
              </div>
              <div class="popup-btn-ctnr t-center">
                <button
                  class="bl-button panel-btn bl-button--primary bl-button--size"><span
                  class="txt">确定</span></button>
                <button
                  class="bl-button panel-btn bl-button--ghost bl-button--size"><span
                  class="txt">取消</span></button>
              </div>
            </div>
            <div role="button" title="关闭面板"
                 class="close-btn p-absolute bg-center bg-no-repeat pointer t-center"><i
              class="bp-icon-font icon-close"></i>
            </div>
          </div><!----></div>
      </div>

    </div>
    <div>
      <div class="im-popup confirm-popup" style="display: none;">
        <div
          class="bp-popup-panel p-relative a-move-in-top a-forwards im-popup-shell"
          style="width: 500px; display: none;">
          <div class="title-ctnr p-relative"><h2
            class="popup-title">确认关闭内容推送吗？</h2>
          </div>
          <div class="popup-content-ctnr">
            <div class="content">
              <div class="content-text">关闭后，你将不再收到该账号的内容推送，但通知类消息不受影响</div>
            </div>
            <div class="popup-btn-ctnr t-center">
              <button
                class="bl-button panel-btn bl-button--primary bl-button--size"><span
                class="txt">确定</span></button>
              <button
                class="bl-button panel-btn bl-button--ghost bl-button--size"><span
                class="txt">取消</span></button>
            </div>
          </div>
          <div role="button" title="关闭面板"
               class="close-btn p-absolute bg-center bg-no-repeat pointer t-center"><i
            class="bp-icon-font icon-close"></i>
          </div>
        </div><!----></div>
    </div><!----><!----></div>
</template>

<script>

import RecordApi from '@/api/message-center/record'

export default {

  data() {
    return{
      whisperId : "",
      whisperName: "",
      whisperAvatar: "",
      messageList : [],
      textContent : "",
      messageSocket: null,
    }
  },
  props :{
    currentUser : {
      type : Object
    }
  },
  watch : {
    whisperId : {
      handler:function(newVal,oldVal){
        console.log("私聊对象改变...")
        this.messageList = [];
        this.getRecords(1,0);
        if (oldVal == "")
          this.initWebSocket();
      },
    }
  },
  methods : {
    // 初始化weosocket
    initWebSocket() {
      if (typeof (WebSocket) === "undefined") {
        console.log("您的浏览器不支持WebSocket")
      } else {
        const wsurl = "ws://localhost:8000/chatWebSocket/"+this.currentUser.id;
        // 实例化 WebSocket
        this.messageSocket = new WebSocket(wsurl);
        // 监听 WebSocket 连接
        this.messageSocket.onopen = this.websocketonopen;
        // 监听 WebSocket 错误信息
        this.messageSocket.onerror = this.websocketonerror;
        // 监听 WebSocket 消息
        this.messageSocket.onmessage = this.websocketonmessage;

        this.messageSocket.onclose = this.websocketclose;

        console.log(this.messageSocket)
      }
    },
    websocketonopen() {
      console.log("连接成功");
    },
    // 连接建立失败重连
    websocketonerror() {
      console.log("连接错误");
      // this.initWebSocket();
    },
    // 数据接收
    websocketonmessage(res) {
      console.log("数据接收");
      console.log(res)
      // var item  = {
      //   senderId : this.whisperId,
      //   senderName : this.whisperName,
      //   receiverId:  this.currentUser.id,
      //   receiverName: this.currentUser.userName,
      //   msgContent : res.data
      // }

      var item = JSON.parse(res.data)
      if (item.senderId == this.whisperId)
        this.messageList.push(item)
    },
    // 数据发送
    websocketsend(message) {
      var data = {'toUid': this.friendUID, 'content': message}
      this.messageSocket.send(JSON.stringify(data));
    },
    // 关闭
    websocketclose(e) {
      console.log('WebSocket 断开连接', e);
    },
    getRecords(index,newMsgCount){
        this.$axios.post('/api/record/getRecords',
          "userId="+this.currentUser.id
          +"&oppositeUserId="+this.whisperId
          +"&index="+index
          +"&newMsgCount="+newMsgCount
        )
          .then(res=>{
            console.log(res.data)
            for (let i = 0; i < res.data.data.length; i++) {
              this.messageList.unshift(res.data.data[i])
            }
          })
    },
    sendMsg(){
      var param = {
        msgType : "text",
        senderId : this.currentUser.id,
        senderName : this.currentUser.userName,
        receiverId : this.whisperId,
        receiverName : this.whisperName,
        msgContent : this.textContent,
        msgTime : new Date().getTime(),
      }
      this.messageList.push(param)
      this.textContent = ""
      console.log(param)
      RecordApi.sendMsg(param).then(res =>{

      })
    }
  },
  created() {
    console.log("created...")
  },
  beforeMount() {
    console.log("beforeMount...")
  },
  destroyed() {
    //离开路由之后断开 websocket 连接
    this.messageSocket.close();
  }
  ,
  mounted() {
    console.log("mounted...")
    //全局总线事件接受,聊天对象信息
    this.$bus.$on('concrete-whisper', (data) => {
      console.log("接收到数据...")
      console.log(data)
      this.whisperId = data.oppositeUserId;
      this.whisperName = data.oppositeUserName;
      this.whisperAvatar = data.oppositeUserPhoto;
    });
  }
}
</script>

<style>
.gm-scrollbar-window {
  position: relative;
  overflow: hidden!important;
  height: 600px;
  width: 780px;
}
</style>
