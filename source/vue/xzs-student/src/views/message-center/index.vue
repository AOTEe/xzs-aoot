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
              <router-link to="/message/reply" class="item">回复我的</router-link>
              <router-link to="/message/at" class="item">@ 我的</router-link>
              <router-link to="/message/love" class="item">收到的赞</router-link>
              <router-link to="/message/system" class="item">系统通知</router-link>
              <router-link to="/message/whisper" class="item">我的消息</router-link>
              <!--              <li class="item"><a href="#/reply" class="" title="回复我的">回复我的&lt;!&ndash;&ndash;&gt;</a></li>-->
              <!--              <li class="item"><a href="#/at" class="" title="@我的">@ 我的&lt;!&ndash;&ndash;&gt;</a></li>-->
              <!--              <li class="item"><a href="#/love" class="" title="收到的赞">收到的赞&lt;!&ndash;&ndash;&gt;</a></li>-->
              <!--              <li class="item"><a href="#/system" class="" title="系统通知">系统通知&lt;!&ndash;&ndash;&gt;</a></li>-->
              <!--              <li class="item"><a href="#/whisper" class="" title="我的消息">我的消息&lt;!&ndash;&ndash;&gt;</a></li>-->
            </ul>
          </div>
        </div>
        <div class="space-right">
          <router-view></router-view>
        </div>
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
