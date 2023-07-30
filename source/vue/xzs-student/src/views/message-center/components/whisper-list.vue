<template>
  <div class="left">
    <div title="如果您的消息展示异常，请按住 alt 按键，然后单击这里进行修复" class="title-left">
      <span>近期消息</span>
    </div>
    <div class="list-container ps ps--active-y">

      <div class="list">
        <GeminiScrollbar  class="gm-scrollbar-list">
        <div class="list-item" v-for="item in whisperList" @click="clickItem(item)">
          <div class="avatar" :title="item.oppositeUserName"></div>
          <div class="name-box">
            <div class="name" :title="item.oppositeUserName">{{ item.oppositeUserName }}</div>
            <div :title="item.lastMsg" class="last-word">{{ item.lastMsg }}
            </div>
          </div>
          <div class="close">
            <svg viewBox="0 0 40 40" class="css-1dtzbno">
              <path
                d="M22.83,20,38.42,4.41a2,2,0,1,0-2.83-2.83h0L20,17.17,4.41,1.58A2,2,0,0,0,1.58,4.41L17.17,20,1.58,35.59a2,2,0,0,0,2.83,2.83L20,22.83,35.59,38.42a2,2,0,1,0,2.83-2.83Z"></path>
            </svg>
          </div>
        </div>
        </GeminiScrollbar>
      </div>

<!--      <div class="ps__rail-x" style="left: 0px; bottom: 0px;">-->
<!--        <div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div>-->
<!--      </div>-->
<!--      <div class="ps__rail-y" style="top: 0px; right: 0px; height: 1118px;">-->
<!--        <div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 240px;"></div>-->
<!--      </div>-->
    </div>
  </div>
</template>
<script>
import Vue from 'vue'
import GeminiScrollbar from 'vue-gemini-scrollbar'
import RecordApi from '@/api/message-center/record'

Vue.use(GeminiScrollbar)
export default {
  comments:{
    GeminiScrollbar
  },
  data() {
    return {
      whisperList: [],
      /**
       * userId、name、avatar、lastWord
       */
    }
  },
  props:{
    currentUser : {
      type : Object
    }
  },
  methods:{
    clickItem(itemData){
      console.log("itemData")
      console.log(itemData)
      console.log(this.$bus)
      this.$bus.$emit('concrete-whisper',itemData);
    },
    initWhisperList(){
      this.$axios.post('/api/record/getRecentChats',"userId="+this.currentUser.id)
        .then(res=>{
          console.log(res.data)
          this.whisperList = res.data.data;
        })
      // this.$axios({
      //   method: 'post',
      //   url: '/api/record/getRecentChats',
      //   headers: {
      //     'content-type': 'multipart/form-data'
      //   },
      //   data: {
      //     userId : this.currentUser.id
      //   }
      // }).then(res => {
      //   console.log(res.data)
      //   this.whisperList = res.data;
      // }, err => {
      //   console.log(err);
      // })
    }

  },
  watch:{
    currentUser:{
      handler:function(newVal,oldVal){
        console.log(newVal)
        if (newVal !=null)
          this.initWhisperList()
      },
      deep:true,
    }
  },
  created() {
  },
}
</script>
<style>
@import '../css/message.css';

.gm-scrollbar-list {
  position: relative;
  overflow: hidden!important;
  height: 770px;
}
/*
 * Container style
 */
.ps {
  overflow: hidden !important;
  overflow-anchor: none;
  -ms-overflow-style: none;
  touch-action: auto;
  -ms-touch-action: auto;
}

/*
 * Scrollbar rail styles
 */
.ps__rail-x {
  display: none;
  opacity: 0;
  transition: background-color .2s linear, opacity .2s linear;
  -webkit-transition: background-color .2s linear, opacity .2s linear;
  height: 15px;
  /* there must be 'bottom' or 'top' for ps__rail-x */
  bottom: 0px;
  /* please don't change 'position' */
  position: absolute;
}

.ps__rail-y {
  display: none;
  opacity: 0;
  transition: background-color .2s linear, opacity .2s linear;
  -webkit-transition: background-color .2s linear, opacity .2s linear;
  width: 15px;
  /* there must be 'right' or 'left' for ps__rail-y */
  right: 0;
  /* please don't change 'position' */
  position: absolute;
}

.ps--active-x > .ps__rail-x,
.ps--active-y > .ps__rail-y {
  display: block;
  background-color: transparent;
}

.ps:hover > .ps__rail-x,
.ps:hover > .ps__rail-y,
.ps--focus > .ps__rail-x,
.ps--focus > .ps__rail-y,
.ps--scrolling-x > .ps__rail-x,
.ps--scrolling-y > .ps__rail-y {
  opacity: 0.6;
}

.ps .ps__rail-x:hover,
.ps .ps__rail-y:hover,
.ps .ps__rail-x:focus,
.ps .ps__rail-y:focus,
.ps .ps__rail-x.ps--clicking,
.ps .ps__rail-y.ps--clicking {
  background-color: #eee;
  opacity: 0.9;
}

/*
 * Scrollbar thumb styles
 */
.ps__thumb-x {
  background-color: #aaa;
  border-radius: 6px;
  transition: background-color .2s linear, height .2s ease-in-out;
  -webkit-transition: background-color .2s linear, height .2s ease-in-out;
  height: 6px;
  /* there must be 'bottom' for ps__thumb-x */
  bottom: 2px;
  /* please don't change 'position' */
  position: absolute;
}

.ps__thumb-y {
  background-color: #aaa;
  border-radius: 6px;
  transition: background-color .2s linear, width .2s ease-in-out;
  -webkit-transition: background-color .2s linear, width .2s ease-in-out;
  width: 6px;
  /* there must be 'right' for ps__thumb-y */
  right: 2px;
  /* please don't change 'position' */
  position: absolute;
}

.ps__rail-x:hover > .ps__thumb-x,
.ps__rail-x:focus > .ps__thumb-x,
.ps__rail-x.ps--clicking .ps__thumb-x {
  background-color: #999;
  height: 11px;
}

.ps__rail-y:hover > .ps__thumb-y,
.ps__rail-y:focus > .ps__thumb-y,
.ps__rail-y.ps--clicking .ps__thumb-y {
  background-color: #999;
  width: 11px;
}

/* MS supports */
@supports (-ms-overflow-style: none) {
  .ps {
    overflow: auto !important;
  }
}

@media screen and (-ms-high-contrast: active), (-ms-high-contrast: none) {
  .ps {
    overflow: auto !important;
  }
}


/*# sourceMappingURL=vendors~index.1.86c83d32.css.map*/
</style>
