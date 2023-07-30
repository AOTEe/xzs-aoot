<template>
  <div class="container">
    <div id="link-message-container" class="">
      <div class="container-sub">
        <div class="space-left">
          <div class="side-bar">
            <div class="title">
              <div class="icon"></div>
              <div>消息中心</div>
            </div>
            <ul class="list">
              <li class="item"><a href="#/reply" class="" title="回复我的">回复我的<!----></a></li>
              <li class="item"><a href="#/at" class="" title="@我的">@ 我的<!----></a></li>
              <li class="item"><a href="#/love" class="" title="收到的赞">收到的赞<!----></a></li>
              <li class="item"><a href="#/system" class="" title="系统通知">系统通知<!----></a></li>
              <li class="item"><a href="#/whisper" class="" title="我的消息">我的消息<!----></a></li>
            </ul>
          </div>
        </div>
        <whisper></whisper>
      </div>
    </div>
  </div>
</template>
<script>
import whisper from './whisper'
export default {

  components: {
    whisper
  }
}
</script>
<style>
@import "css/message.css";

</style>
