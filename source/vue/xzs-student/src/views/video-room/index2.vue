<template>
  <div>

    <!--    头部导航-视频类型-->


    <!--    视频项-每行5个-->
    <div class="card various-c-card"   >
      <div class="storey-content">
        <div
          class="s-c-item"
          v-for="(item, i) in douga"
          :key="i"
          @click="jumpPath(item.title)"
        >
          <!-- 图片 -->
          <div class="item-pic">
            <!-- 3.图片懒加载修改img :src -> v-lazy -->
            <img src="../../assets/img/0d107f700355278136e1ca2cb8a4e78bd9f8dd2e.jpg" alt="" />
            <div class="count">
              <i class="iconfont icon-bofangliang1"></i>
              {{ tenThousand(item.stat.view) }}
              <i class="iconfont icon-dianzan"></i>
              {{ tenThousand(item.stat.like) }}
              <i class="iconfont icon-jinbi"></i>
              {{ tenThousand(item.stat.coin) }}
            </div>
            <div class="duration">
              {{ timeHandle(item.duration) }}
            </div>
          </div>
          <!-- 标题 -->
          <div class="item-title">
            <a href="#">{{ item.title }}</a>
          </div>
          <!-- up主 -->
          <div class="item-up">
            <a href="#">
              <i class="iconfont icon-icon_bilibili-circle"></i>
              {{ item.owner.name }}
            </a>
          </div>
        </div>
      </div>
    </div>


  </div>
</template>

<script>
export default {
  data() {
    return {
      douga: [
        {
          "aid": 318062844,
          "videos": 1,
          "tid": 27,
          "tname": "\u7efc\u5408",
          "copyright": 1,
          "pic": "http:\/\/i0.hdslb.com\/bfs\/archive\/1336e11f4e63cdeaf06dbcec2a21e8d4898b824f.jpg",
          "title": "\u8d85\u7ba1\u88ab\u6211\u53d1\u73b0\u517c\u804c\u505a\u7247\u54e5\uff01\u8fd8\u7231\u770b\u5973\u4e3b\u64ad\uff01",
          "pubdate": 1693879200,
          "ctime": 1693716298,
          "desc": "VUP\uff1a\u5154\u7720\u53ef\u53ef\n\u540e\u671f\uff1a\u7cd6\u679c\u6c14\u606f\n\n\u53d1\u8a00\u90fd\u662f\u8282\u76ee\u6548\u679c\u634f\uff01\n\u4e00\u952e\u4e09\u8fde\u634f\uff01\u4e3b\u4eba\uff01\n\n\u2014\u2014\u2014\u2014\u2014\u2014 \u0e05\u055e\u2022 \u2022\u055e\u0e05 \u2014\u2014\u2014\u2014\u2014\u2014\u2014\n\n\u76f4\u64ad\u95f4\u94fe\u63a5\uff1ahttp:\/\/live.bilibili.com\/24978909\n\u76f4\u64ad\u65f6\u95f4\uff08\u672c\u5468\u5b89\u6392\u8bf7\u89c1\u52a8\u6001\u7f6e\u9876\uff09\uff1a\n\u5468\u4e00\u5230\u5468\u4e94\uff0c\u65e9\u4e0a\u516b\u70b9\uff0c\u665a\u4e0a\u516b\u70b9\u534a\n\u5468\u516d\u5468\u65e5\uff0c\u4ece\u65e9\u5230\u665a",
          "state": 0,
          "attribute": 16512,
          "duration": 183,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 0,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 1096034982,
            "name": "\u5154\u7720\u53ef\u53ef",
            "face": "https:\/\/i0.hdslb.com\/bfs\/face\/9bcdfb9d809865e6662b3450f9a632bee4a7ba3a.jpg"
          },
          "stat": {
            "aid": 318062844,
            "view": 277579,
            "danmaku": 70,
            "reply": 152,
            "favorite": 5347,
            "coin": 390,
            "share": 238,
            "now_rank": 0,
            "his_rank": 0,
            "like": 11533,
            "dislike": 0,
            "vt": 0,
            "vv": 277579
          },
          "dynamic": "",
          "cid": 1255760720,
          "dimension": {
            "width": 3840,
            "height": 2160,
            "rotate": 0
          },
          "season_id": 565140,
          "attribute_v2": 65536,
          "short_link_v2": "https:\/\/b23.tv\/BV1BP411h7iH",
          "first_frame": "http:\/\/i0.hdslb.com\/bfs\/storyff\/n230903sa1lgxprlaefc62275zh64xl6_firsti.jpg",
          "pub_location": "\u4e0a\u6d77",
          "bvid": "BV1BP411h7iH",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        },
        {
          "aid": 532906886,
          "videos": 1,
          "tid": 253,
          "tname": "\u52a8\u6f2b\u6742\u8c08",
          "copyright": 1,
          "pic": "http:\/\/i1.hdslb.com\/bfs\/archive\/81c4c786209ff8aa5f96e7cd1ab6cf8a67b038c3.jpg",
          "title": "\u4ee4\u4eba\u7ec6\u601d\u6781\u6050\u7684\u65b0\u751f\u4ee3\u6050\u6016\u60ac\u7591\u6f2b\u753b \u80fd\u5426\u6210\u4e3a\u4f0a\u85e4\u6da6\u4e8c\u7684\u540e\u7ee7\uff1f feat.\u6211\u6b7b\u524d\u7684\u767e\u7269\u8bed\uff5c\u971c\u6708",
          "pubdate": 1693384536,
          "ctime": 1693384536,
          "desc": "\u611f\u8c22\u6536\u770b\uff01\u559c\u6b22\u8bf7\u4e09\u8fde\uff01\n\u6f2b\u753b\uff1a\u50d5\u304c\u6b7b\u306c\u3060\u3051\u306e\u767e\u7269\u8a9e\uff08\u4e2d\u8bd1\u6682\u5b9a\uff1a\u6211\u6b7b\u524d\u7684\u767e\u7269\u8bed\uff09\n\u56fe\u6e90\u7ffb\u8bd1\uff1a\u8fd9\u4e2a\u6f2b\u753b\u7adf\u7136\u6ca1\u4eba\u6c49\u5316\u6c49\u5316\u7ec4\uff08\u6682\u5b9a\uff09\nBgm\uff1aAmazarashi - \u53e4\u3044\uff33\uff26\u6620\u753b (Starlight ver.)",
          "state": 0,
          "attribute": 16768,
          "duration": 725,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 1,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 11290605,
            "name": "\u5341\u516d\u591c\u971c\u6708",
            "face": "https:\/\/i0.hdslb.com\/bfs\/face\/00222681a764ea293bccd9a7316aaf7e5a82b85c.jpg"
          },
          "stat": {
            "aid": 532906886,
            "view": 6015,
            "danmaku": 75,
            "reply": 37,
            "favorite": 178,
            "coin": 78,
            "share": 6,
            "now_rank": 0,
            "his_rank": 0,
            "like": 257,
            "dislike": 0,
            "vt": 0,
            "vv": 6015
          },
          "dynamic": "",
          "cid": 1251724418,
          "dimension": {
            "width": 1920,
            "height": 1080,
            "rotate": 0
          },
          "attribute_v2": 65536,
          "short_link_v2": "https:\/\/b23.tv\/BV15u411K798",
          "first_frame": "http:\/\/i2.hdslb.com\/bfs\/storyff\/n230830a274yybzpnke8t35djizm0ewq_firsti.jpg",
          "pub_location": "\u91cd\u5e86",
          "bvid": "BV15u411K798",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        },
        {
          "aid": 320600856,
          "videos": 1,
          "tid": 27,
          "tname": "\u7efc\u5408",
          "copyright": 1,
          "pic": "http:\/\/i1.hdslb.com\/bfs\/archive\/0d107f700355278136e1ca2cb8a4e78bd9f8dd2e.jpg",
          "title": "\u7ae5\u5e74\u73a9\u5177\u56de\u5fc6\uff1a\u6218\u6597\u9640\u87ba",
          "pubdate": 1693825602,
          "ctime": 1693825602,
          "desc": "\u7ae5\u5e74\u73a9\u5177\u56de\u5fc6-\u5f39\u73e0\u8d85\u4eba\uff1ahttps:\/\/b23.tv\/72MI8HT\n\n\u7ae5\u5e74\u73a9\u5177\u56de\u5fc6-\u5f39\u73e0\u8d85\u4eba\u6240\u6709\u89d2\u8272\u5a01\u529b\u5b9e\u6d4b\uff1ahttps:\/\/b23.tv\/n69njH6",
          "state": 0,
          "attribute": 16768,
          "duration": 788,
          "mission_id": 1695285,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 1,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 10682915,
            "name": "OnionMan",
            "face": "https:\/\/i2.hdslb.com\/bfs\/face\/d6a30341b831626704582a95a08912b95c72fc53.jpg"
          },
          "stat": {
            "aid": 320600856,
            "view": 43673,
            "danmaku": 429,
            "reply": 413,
            "favorite": 661,
            "coin": 673,
            "share": 301,
            "now_rank": 0,
            "his_rank": 0,
            "like": 2782,
            "dislike": 0,
            "vt": 0,
            "vv": 43673
          },
          "dynamic": "",
          "cid": 1257127580,
          "dimension": {
            "width": 1920,
            "height": 1080,
            "rotate": 0
          },
          "attribute_v2": 65536,
          "short_link_v2": "https:\/\/b23.tv\/BV1tw411D7FU",
          "up_from_v2": 20,
          "first_frame": "http:\/\/i2.hdslb.com\/bfs\/storyff\/n230904a210h1k2iyk9yjy1libaao1if_firsti.jpg",
          "pub_location": "\u5317\u4eac",
          "bvid": "BV1tw411D7FU",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        },
        {
          "aid": 954387382,
          "videos": 1,
          "tid": 27,
          "tname": "\u7efc\u5408",
          "copyright": 1,
          "pic": "http:\/\/i0.hdslb.com\/bfs\/archive\/59b37e3e28ba802ef20089a7de590f55174c2bad.jpg",
          "title": "\u4e1c\u65b9project\u4eba\u6c14\u6700\u9ad8\u7684\u4e8c\u5341\u9996\u795e\u66f2\uff0c\u4f60\u662f\u5426\u6709\u6240\u8033\u95fb\uff1f\uff1f",
          "pubdate": 1685937000,
          "ctime": 1685937000,
          "desc": "\u6c42\u63a8\u8350\uff0c\u6c42\u63a8\u8350\uff01\uff01\n\n\u6392\u540d\u4f9d\u7167\u7f51\u6613\u4e91QQ\u9177\u72d7\u8bc4\u8bba\u6570\u4e4b\u548c\u8fdb\u884c\u6392\u540d\uff0c\u53d6\u524d20\u540d\uff08\u6570\u636e\u76d8\u70b9\u65f6\u95f42023\u5e746\u67081\u65e5\uff09\n\n\u6b4c\u66f2\u8bc4\u8bba\u6570\u5728\u4e00\u5b9a\u7a0b\u5ea6\u4e0a\u76f8\u5bf9\u5ba2\u89c2\u7684\u53cd\u5e94\u97f3\u4e50\u548c\u4f5c\u54c1\u7684\u70ed\u5ea6\u548c\u4eba\u6c14\uff0c\u4f46\u6570\u636e\u7edf\u8ba1\u6392\u540d\u4ec5\u63d0\u4f9b\u53c2\u8003\uff08\u4f60\u5fc3\u4e2d\u7684\u7b2c\u4e00\u624d\u662f\u771f\u6b63\u7684\u7b2c\u4e00\uff09\u4e0d\u5305\u62ec\u7eaf\u97f3\u4e50\n\n\u5ba1\u6838\u8f9b\u82e6\u4e86\uff01\u7edf\u4e00\u914d\u7f6e\u7684\u5b57\u5e55\uff0c\u5236\u4f5c\u4e0d\u6613\uff0c\u559c\u6b22\u7684\u670b\u53cb\uff0c\u671b\u4e09\u8fde\u652f\u6301\u4e0b",
          "state": 0,
          "attribute": 16768,
          "duration": 2609,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 1,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 106339948,
            "name": "\u68a6\u7eef\u4e16",
            "face": "https:\/\/i1.hdslb.com\/bfs\/face\/6385114fcaedd521af4f2f020b5202b83d4096df.jpg"
          },
          "stat": {
            "aid": 954387382,
            "view": 274015,
            "danmaku": 2182,
            "reply": 1141,
            "favorite": 19023,
            "coin": 8658,
            "share": 1202,
            "now_rank": 0,
            "his_rank": 0,
            "like": 18907,
            "dislike": 0,
            "vt": 0,
            "vv": 274015
          },
          "dynamic": "",
          "cid": 1154786875,
          "dimension": {
            "width": 1920,
            "height": 1080,
            "rotate": 0
          },
          "season_id": 1518801,
          "attribute_v2": 65536,
          "short_link_v2": "https:\/\/b23.tv\/BV1xW4y1R7HL",
          "first_frame": "http:\/\/i0.hdslb.com\/bfs\/storyff\/n230606a23dokj8z1wlzit1as7bebgci_firsti.jpg",
          "pub_location": "\u798f\u5efa",
          "bvid": "BV1xW4y1R7HL",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        },
        {
          "aid": 914084195,
          "videos": 1,
          "tid": 25,
          "tname": "MMD\u00b73D",
          "copyright": 1,
          "pic": "http:\/\/i1.hdslb.com\/bfs\/archive\/6b21e33fb28b943f28f40444364f1fbb2dceaf7b.jpg",
          "title": "\u3010\u666f\u5203\/\u5267\u60c5\u5411MMD\u3011\u975e\u5e38\u9057\u61be\u3001\u975e\u5e38\u6b63\u786e",
          "pubdate": 1690730980,
          "ctime": 1690730980,
          "desc": "\u5267\u60c5\u90fd\u662f\u6211\u7f16\u7684\n\u6709\u90e8\u5206\u4e915\uff084\uff09\uff0c\u4f46\u662f\u662f\u666f\u5203\n\u5267\u60c5\u90fd\u662f\u6211\u7f16\u7684\uff08\u518d\u6b21\uff09\n\u8865\u4e00\u4e0b\u6b4c\uff1aPSYQUI - \u30d2\u30b9\u30c6\u30ea\u30c3\u30af\u30ca\u30a4\u30c8\u30ac\u30fc\u30eb -Awakening- (feat. Such)",
          "state": 0,
          "attribute": 16512,
          "duration": 86,
          "mission_id": 1676821,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 0,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 2727576,
            "name": "Yumekawa-se17",
            "face": "https:\/\/i0.hdslb.com\/bfs\/face\/f88a416e5d6234ac26535a89d85d8d54e20c273e.jpg"
          },
          "stat": {
            "aid": 914084195,
            "view": 106610,
            "danmaku": 129,
            "reply": 240,
            "favorite": 10155,
            "coin": 5648,
            "share": 1051,
            "now_rank": 0,
            "his_rank": 0,
            "like": 12746,
            "dislike": 0,
            "vt": 0,
            "vv": 106610
          },
          "dynamic": "",
          "cid": 1216372758,
          "dimension": {
            "width": 1280,
            "height": 720,
            "rotate": 0
          },
          "attribute_v2": 65536,
          "short_link_v2": "https:\/\/b23.tv\/BV1iM4y1W7ds",
          "first_frame": "http:\/\/i1.hdslb.com\/bfs\/storyff\/n230730a23kmaorj4yuyg737smouja5g_firsti.jpg",
          "pub_location": "\u8fbd\u5b81",
          "bvid": "BV1iM4y1W7ds",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        },
        {
          "aid": 318071206,
          "videos": 1,
          "tid": 47,
          "tname": "\u77ed\u7247\u00b7\u624b\u4e66",
          "copyright": 1,
          "pic": "http:\/\/i2.hdslb.com\/bfs\/archive\/c3ff68b3ea36e6331a78298954322f90b6e81e61.jpg",
          "title": "\u2764\ufe0f\u770b \u770b \u4f60 \u54e5 \u54e5 \u7684\u2764\ufe0f",
          "pubdate": 1693749351,
          "ctime": 1693749352,
          "desc": "staff\n\n\u5267\u672c\/\u539f\u753b\/\u540e\u671f\uff1a\u67f3\u5ddd\u732b\n\n\u914d\u97f3\n\u58f0\u51fa\u6f14\n\n\u6797\u5c3c\uff1a@xyxy\u5c0f\u5b87  \n\n\u516c\u5b50\/\u7a7a\uff1a@\u6478\u9c7c\u7684\u897f\u897f\u5f17\u65af  \n\n\u8367\/\u7433\u59ae\u7279\/\u795e\u91cc\u7eeb\u534e\/\u6258\u514b\uff1a@\u5984\u5d3der  \n\n\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a\u554a\u6211\u4e5f\u60f3\u8981\u4e2a\u54e5\u54e5\u554a\u554a",
          "state": 0,
          "attribute": 16768,
          "duration": 70,
          "mission_id": 1690571,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 1,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 15405531,
            "name": "\u67f3\u5ddd\u732b\u4e0d\u662f\u4eba",
            "face": "https:\/\/i0.hdslb.com\/bfs\/face\/c92a510f8bdf0b4b3e49a7dfbb1a9e4d695355d0.jpg"
          },
          "stat": {
            "aid": 318071206,
            "view": 443426,
            "danmaku": 190,
            "reply": 389,
            "favorite": 36778,
            "coin": 3463,
            "share": 3154,
            "now_rank": 0,
            "his_rank": 0,
            "like": 39019,
            "dislike": 0,
            "vt": 0,
            "vv": 443426
          },
          "dynamic": "\u563f\u563f\u662f\u54e5\u54e5\u634f~\uff08\u5438\u6e9c\uff09",
          "cid": 1256279889,
          "dimension": {
            "width": 1920,
            "height": 1080,
            "rotate": 0
          },
          "season_id": 525700,
          "attribute_v2": 65536,
          "short_link_v2": "https:\/\/b23.tv\/BV1rP411h7M8",
          "first_frame": "http:\/\/i2.hdslb.com\/bfs\/storyff\/n230903sa8d0pbx50s2lj2mvw42g2a57_firsti.jpg",
          "pub_location": "\u5e7f\u4e1c",
          "bvid": "BV1rP411h7M8",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        },
        {
          "aid": 404761249,
          "videos": 1,
          "tid": 27,
          "tname": "\u7efc\u5408",
          "copyright": 1,
          "pic": "http:\/\/i2.hdslb.com\/bfs\/archive\/64e728955c55228e5cd090bd9fb4e20fc7929f66.jpg",
          "title": "\u201c\u5e74\u5c11\u800c\u4e0d\u53ef\u5f97\u4e4b\u7269\uff0c\u7ec8\u4f1a\u56f0\u4ed6\u4e00\u751f\uff01\u201d",
          "pubdate": 1692013901,
          "ctime": 1692013901,
          "desc": "-",
          "state": 0,
          "attribute": 16512,
          "duration": 131,
          "mission_id": 1624960,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 0,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 346749212,
            "name": "wrizedm",
            "face": "https:\/\/i2.hdslb.com\/bfs\/face\/28912f7c3f1593681c96dd39e5bd7efdced1f045.jpg"
          },
          "stat": {
            "aid": 404761249,
            "view": 1417663,
            "danmaku": 433,
            "reply": 1281,
            "favorite": 28094,
            "coin": 11133,
            "share": 4612,
            "now_rank": 0,
            "his_rank": 0,
            "like": 87294,
            "dislike": 0,
            "vt": 0,
            "vv": 1417663
          },
          "dynamic": "",
          "cid": 1233998558,
          "dimension": {
            "width": 1920,
            "height": 1080,
            "rotate": 0
          },
          "attribute_v2": 65536,
          "short_link_v2": "https:\/\/b23.tv\/BV1SG411Z7Ns",
          "first_frame": "http:\/\/i1.hdslb.com\/bfs\/storyff\/n230814sa1n5vgfz190oll1ekobfa0sm_firsti.jpg",
          "pub_location": "\u5317\u4eac",
          "bvid": "BV1SG411Z7Ns",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        },
        {
          "aid": 617508724,
          "videos": 1,
          "tid": 47,
          "tname": "\u77ed\u7247\u00b7\u624b\u4e66",
          "copyright": 1,
          "pic": "http:\/\/i0.hdslb.com\/bfs\/archive\/b54334a-18a210215b1.jpeg",
          "title": "\u3010\u5634\u70ae\u625b\u628a\u5b50 \u4e9a\u6258\u514b\u65af\u301124 \u590f\u65e5\u6cf3\u6c60\u6d3e\u5bf9",
          "pubdate": 1692781200,
          "ctime": 1692771411,
          "desc": "",
          "state": 0,
          "attribute": 16768,
          "duration": 211,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 1,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 190067530,
            "name": "\u9a6c\u514b\u4e36\u4e0d\u5410\u6e29",
            "face": "https:\/\/i1.hdslb.com\/bfs\/face\/fddeb428229318415f1f9a90122bb214e5425752.jpg"
          },
          "stat": {
            "aid": 617508724,
            "view": 446102,
            "danmaku": 642,
            "reply": 342,
            "favorite": 8129,
            "coin": 5887,
            "share": 870,
            "now_rank": 0,
            "his_rank": 0,
            "like": 58573,
            "dislike": 0,
            "vt": 0,
            "vv": 446102
          },
          "dynamic": "",
          "cid": 1244003765,
          "dimension": {
            "width": 1440,
            "height": 1080,
            "rotate": 0
          },
          "season_id": 1266176,
          "attribute_v2": 65536,
          "short_link_v2": "https:\/\/b23.tv\/BV1Z84y1o7ac",
          "first_frame": "http:\/\/i0.hdslb.com\/bfs\/storyff\/n230823sa1efzyk4zjv53c3b13xhb3rs_firsti.jpg",
          "pub_location": "\u91cd\u5e86",
          "bvid": "BV1Z84y1o7ac",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        },
        {
          "aid": 703065626,
          "videos": 1,
          "tid": 210,
          "tname": "\u624b\u529e\u00b7\u6a21\u73a9",
          "copyright": 1,
          "pic": "http:\/\/i2.hdslb.com\/bfs\/archive\/84a65149a22fe60e2be99e47b7f6546f0df1957f.jpg",
          "title": "\u4e00\u6b3e\u79ef\u6728\u4e09\u4e2a\u770b\u70b9\uff01\u9f99\u8fd8\u80fd\u5355\u72ec\u628a\u73a9\uff01\u68ee\u5b9d\u79ef\u6728 \u98de\u9f99\u5728\u5929 \u56fd\u521b\u79ef\u6728\u3010\u795e\u7530\u73a9\u5177\u7ec4\u3011",
          "pubdate": 1693994400,
          "ctime": 1693883982,
          "desc": "\u68ee\u5b9d\u79ef\u6728 \u5f3a\u56fd\u96c4\u98ce\u7cfb\u5217 \u98de\u9f99\u5728\u5929 \u5f00\u7bb1\u5206\u4eab\u3002",
          "state": 0,
          "attribute": 16512,
          "duration": 197,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 0,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 10993427,
            "name": "\u795e\u7530\u7ec4\u957f",
            "face": "https:\/\/i2.hdslb.com\/bfs\/face\/27885057d341be870246d0868bb68715a9996d3c.jpg"
          },
          "stat": {
            "aid": 703065626,
            "view": 7555,
            "danmaku": 27,
            "reply": 28,
            "favorite": 44,
            "coin": 36,
            "share": 21,
            "now_rank": 0,
            "his_rank": 0,
            "like": 637,
            "dislike": 0,
            "vt": 0,
            "vv": 7555
          },
          "dynamic": "\u5de8\u5927\u63a7\u8868\u793a\u4e00\u672c\u6ee1\u8db3~",
          "cid": 1257726312,
          "dimension": {
            "width": 2560,
            "height": 1440,
            "rotate": 0
          },
          "attribute_v2": 128,
          "short_link_v2": "https:\/\/b23.tv\/BV1jm4y1M7VL",
          "first_frame": "http:\/\/i1.hdslb.com\/bfs\/storyff\/n230905sa1r4l20nklujm42twczmu38c_firsti.jpg",
          "pub_location": "\u6c5f\u82cf",
          "bvid": "BV1jm4y1M7VL",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        },
        {
          "aid": 944532207,
          "videos": 1,
          "tid": 27,
          "tname": "\u7efc\u5408",
          "copyright": 1,
          "pic": "http:\/\/i0.hdslb.com\/bfs\/archive\/5daa5163ced81a6d6bc5ea59a0e234c1db26d2d4.jpg",
          "title": "\u3010\u963f\u8428Aza\u3011\u300a\u865a\u62df\u300b\uff5c\u4f60\u662f\u6211\u671d\u5915\u76f8\u4f34 \u89e6\u624b\u53ef\u53ca\u7684\u865a\u62df",
          "pubdate": 1666949636,
          "ctime": 1666949636,
          "desc": "\u4e4b\u524d\u4e00\u76f4\u60f3\u542caza\u5531\u300a\u865a\u62df\u300b\uff0c\u4ed6\u53d1\u5fae\u535a\u8ba9\u5927\u5bb6\u63a8\u6b4c\u7684\u65f6\u5019\u6211\u8fd8\u63a8\u4e86\u8fd9\u9996\uff0c\u540e\u6765\u624d\u53d1\u73b0\u4ed6\u5531\u8fc7\u4e86\n\n\u771f\u7684\u597d\u559c\u6b22\uff0c\u6240\u4ee5\u505a\u4e86\u8fd9\u4e2a\u89c6\u9891\n\n\u5531\u9519\u4e86\u6b4c\u8bcd\u7684\u5730\u65b9\u6211\u6ca1\u6709\u53d8\uff0c\u6253\u7684\u662f\u539f\u672c\u7684\u6b4c\u8bcd\uff0c\u753b\u9762\u7528\u6211\u7c97\u7cd9\u7684\u624b\u6cd5\u66ff\u6362\u6210\u4e86\u6bd4\u8f83\u7eaf\u51c0\u7684\u7248\u672c\uff0c\u4e2d\u95f4\u8bb2\u8bdd\u7684\u90e8\u5206\u4e5f\u88ab\u6211\u7528\u4f34\u594f\u97f3\u6e90\u66ff\u6362\u4e86\uff0c\u97f3\u9891\u53ef\u80fd\u51fa\u73b0\u8854\u63a5\u6709\u7455\u75b5\u7684\u5730\u65b9\uff0c\u603b\u4f53\u6765\u8bf4\u6211\u8fd8\u662f\u6ee1\u610f\u7684\n\u671f\u5f85\u4ed6\u6709\u4e00\u5929\u8fd8\u4f1a\u5531\n\u963f\u8428Aza\u76f4\u64ad\u95f4\uff1ahttps:\/\/live.bilibili.com\/21696950\n\u963f\u8428Aza\u7684\u4e2a\u4eba\u4e3b\u9875\uff1ahttps:\/\/space.bilibili.com\/480680646",
          "state": 0,
          "attribute": 8405120,
          "duration": 233,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 0,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 290575202,
            "name": "\u68c3\u8292\u9152-li",
            "face": "https:\/\/i2.hdslb.com\/bfs\/face\/264c1cc1fc82a4f75b72e050a800aee1afd35a7a.jpg"
          },
          "stat": {
            "aid": 944532207,
            "view": 84580,
            "danmaku": 34,
            "reply": 74,
            "favorite": 4582,
            "coin": 429,
            "share": 344,
            "now_rank": 0,
            "his_rank": 0,
            "like": 4218,
            "dislike": 0,
            "vt": 0,
            "vv": 84580
          },
          "dynamic": "",
          "cid": 874396395,
          "dimension": {
            "width": 2160,
            "height": 1080,
            "rotate": 0
          },
          "attribute_v2": 65536,
          "short_link_v2": "https:\/\/b23.tv\/BV1nW4y1j7fC",
          "up_from_v2": 19,
          "first_frame": "http:\/\/i0.hdslb.com\/bfs\/storyff\/n221028a22x3ynj7jwb6741razniu9bm_firsti.jpg",
          "pub_location": "\u6cb3\u5357",
          "bvid": "BV1nW4y1j7fC",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        },
        {
          "aid": 703065626,
          "videos": 1,
          "tid": 210,
          "tname": "\u624b\u529e\u00b7\u6a21\u73a9",
          "copyright": 1,
          "pic": "http:\/\/i2.hdslb.com\/bfs\/archive\/84a65149a22fe60e2be99e47b7f6546f0df1957f.jpg",
          "title": "\u4e00\u6b3e\u79ef\u6728\u4e09\u4e2a\u770b\u70b9\uff01\u9f99\u8fd8\u80fd\u5355\u72ec\u628a\u73a9\uff01\u68ee\u5b9d\u79ef\u6728 \u98de\u9f99\u5728\u5929 \u56fd\u521b\u79ef\u6728\u3010\u795e\u7530\u73a9\u5177\u7ec4\u3011",
          "pubdate": 1693994400,
          "ctime": 1693883982,
          "desc": "\u68ee\u5b9d\u79ef\u6728 \u5f3a\u56fd\u96c4\u98ce\u7cfb\u5217 \u98de\u9f99\u5728\u5929 \u5f00\u7bb1\u5206\u4eab\u3002",
          "state": 0,
          "attribute": 16512,
          "duration": 197,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 0,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 10993427,
            "name": "\u795e\u7530\u7ec4\u957f",
            "face": "https:\/\/i2.hdslb.com\/bfs\/face\/27885057d341be870246d0868bb68715a9996d3c.jpg"
          },
          "stat": {
            "aid": 703065626,
            "view": 7555,
            "danmaku": 27,
            "reply": 28,
            "favorite": 44,
            "coin": 36,
            "share": 21,
            "now_rank": 0,
            "his_rank": 0,
            "like": 637,
            "dislike": 0,
            "vt": 0,
            "vv": 7555
          },
          "dynamic": "\u5de8\u5927\u63a7\u8868\u793a\u4e00\u672c\u6ee1\u8db3~",
          "cid": 1257726312,
          "dimension": {
            "width": 2560,
            "height": 1440,
            "rotate": 0
          },
          "attribute_v2": 128,
          "short_link_v2": "https:\/\/b23.tv\/BV1jm4y1M7VL",
          "first_frame": "http:\/\/i1.hdslb.com\/bfs\/storyff\/n230905sa1r4l20nklujm42twczmu38c_firsti.jpg",
          "pub_location": "\u6c5f\u82cf",
          "bvid": "BV1jm4y1M7VL",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        },
        {
          "aid": 944532207,
          "videos": 1,
          "tid": 27,
          "tname": "\u7efc\u5408",
          "copyright": 1,
          "pic": "http:\/\/i0.hdslb.com\/bfs\/archive\/5daa5163ced81a6d6bc5ea59a0e234c1db26d2d4.jpg",
          "title": "\u3010\u963f\u8428Aza\u3011\u300a\u865a\u62df\u300b\uff5c\u4f60\u662f\u6211\u671d\u5915\u76f8\u4f34 \u89e6\u624b\u53ef\u53ca\u7684\u865a\u62df",
          "pubdate": 1666949636,
          "ctime": 1666949636,
          "desc": "\u4e4b\u524d\u4e00\u76f4\u60f3\u542caza\u5531\u300a\u865a\u62df\u300b\uff0c\u4ed6\u53d1\u5fae\u535a\u8ba9\u5927\u5bb6\u63a8\u6b4c\u7684\u65f6\u5019\u6211\u8fd8\u63a8\u4e86\u8fd9\u9996\uff0c\u540e\u6765\u624d\u53d1\u73b0\u4ed6\u5531\u8fc7\u4e86\n\n\u771f\u7684\u597d\u559c\u6b22\uff0c\u6240\u4ee5\u505a\u4e86\u8fd9\u4e2a\u89c6\u9891\n\n\u5531\u9519\u4e86\u6b4c\u8bcd\u7684\u5730\u65b9\u6211\u6ca1\u6709\u53d8\uff0c\u6253\u7684\u662f\u539f\u672c\u7684\u6b4c\u8bcd\uff0c\u753b\u9762\u7528\u6211\u7c97\u7cd9\u7684\u624b\u6cd5\u66ff\u6362\u6210\u4e86\u6bd4\u8f83\u7eaf\u51c0\u7684\u7248\u672c\uff0c\u4e2d\u95f4\u8bb2\u8bdd\u7684\u90e8\u5206\u4e5f\u88ab\u6211\u7528\u4f34\u594f\u97f3\u6e90\u66ff\u6362\u4e86\uff0c\u97f3\u9891\u53ef\u80fd\u51fa\u73b0\u8854\u63a5\u6709\u7455\u75b5\u7684\u5730\u65b9\uff0c\u603b\u4f53\u6765\u8bf4\u6211\u8fd8\u662f\u6ee1\u610f\u7684\n\u671f\u5f85\u4ed6\u6709\u4e00\u5929\u8fd8\u4f1a\u5531\n\u963f\u8428Aza\u76f4\u64ad\u95f4\uff1ahttps:\/\/live.bilibili.com\/21696950\n\u963f\u8428Aza\u7684\u4e2a\u4eba\u4e3b\u9875\uff1ahttps:\/\/space.bilibili.com\/480680646",
          "state": 0,
          "attribute": 8405120,
          "duration": 233,
          "rights": {
            "bp": 0,
            "elec": 0,
            "download": 0,
            "movie": 0,
            "pay": 0,
            "hd5": 0,
            "no_reprint": 1,
            "autoplay": 1,
            "ugc_pay": 0,
            "is_cooperation": 0,
            "ugc_pay_preview": 0,
            "no_background": 0,
            "arc_pay": 0,
            "pay_free_watch": 0
          },
          "owner": {
            "mid": 290575202,
            "name": "\u68c3\u8292\u9152-li",
            "face": "https:\/\/i2.hdslb.com\/bfs\/face\/264c1cc1fc82a4f75b72e050a800aee1afd35a7a.jpg"
          },
          "stat": {
            "aid": 944532207,
            "view": 84580,
            "danmaku": 34,
            "reply": 74,
            "favorite": 4582,
            "coin": 429,
            "share": 344,
            "now_rank": 0,
            "his_rank": 0,
            "like": 4218,
            "dislike": 0,
            "vt": 0,
            "vv": 84580
          },
          "dynamic": "",
          "cid": 874396395,
          "dimension": {
            "width": 2160,
            "height": 1080,
            "rotate": 0
          },
          "attribute_v2": 65536,
          "short_link_v2": "https:\/\/b23.tv\/BV1nW4y1j7fC",
          "up_from_v2": 19,
          "first_frame": "http:\/\/i0.hdslb.com\/bfs\/storyff\/n221028a22x3ynj7jwb6741razniu9bm_firsti.jpg",
          "pub_location": "\u6cb3\u5357",
          "bvid": "BV1nW4y1j7fC",
          "season_type": 0,
          "is_ogv": false,
          "ogv_info": null,
          "rcmd_reason": "",
          "enable_vt": 0
        }
        ],

    };
  },
  computed: {
    // 计算数量过万就转换
    tenThousand() {
      // 计算属性是一个属性不是一个方法, 使用函数返回
      return function (count) {
        if (count >= 100000000) {
          return (count / 100000000).toFixed(1) + "亿";
        }
        return count >= 10000 ? (count / 10000).toFixed(1) + "万" : count;
      };
    },
  },
  methods: {
    // 获取标题名字
    getName() {
      // 如果 this.maindataItem 里面有 tname 这个属性就 return ture取反(false)
      // every() 如果数组中检测到有一个元素不满足，则整个表达式返回 false ，且剩余的元素不会再进行检测。
      if (this.maindataItem.every((item) => !("tname" in item))) {
        return;
      }
      return this.maindataItem[0].tname;
    },

    // 点击换一换
    changeIt() {
      // 事件总线 发射:this.$bus.$emit('aaaa')  接收:this.$bus.$on('aaaa',function)
      this.$bus.$emit("changeList", this.mdnameItem);
    },

    // 处理时间
    timeHandle(time) {
      let timeFormat = "";
      function allTime(time) {
        if (time < 60) {
          let s = time > 0 ? time : "";
          s = s <= 10 ? "0" + s : s;
          timeFormat = timeFormat + s;
          return;
        } else if (time < 3600) {
          let m = Math.floor(time / 60);
          m = m <= 10 ? "0" + m : m;
          timeFormat = timeFormat + m + ":";
          allTime(time - m * 60);
        } else {
          let h = Math.floor(time / 3600);
          h = h <= 10 ? "0" + h : h;
          timeFormat = timeFormat + h + ":";
          allTime(time - h * 3600);
        }
      }
      allTime(time);
      return timeFormat;
    },

    // 点击跳转
    jumpPath(keyword) {
      this.$router.push(`/video/10086`);
    },
  },
}
</script>
<style>
a {
  cursor: pointer;
  text-decoration: none;
  color: #212121;
}
img {
  cursor: pointer;
}
.various-c-card {
  margin: 0 auto;
  padding: 5%;
  width: 90%;
}
.card-list {
  width: 100%;
  font: 12px Helvetica Neue, Helvetica, Arial, Microsoft Yahei, Hiragino Sans GB,
  Heiti SC, WenQuanYi Micro Hei, sans-serif;
}
/* 楼层标题区域 */
.storey-title {
  padding: 10px;
  display: flex;
  justify-content: space-between;
}
.s-t-left {
  display: flex;
  align-items: center;
  font-size: 20px;
  cursor: pointer;
}
.s-t-left i {
  font-size: 28px;
  color: #7b78eb;
  margin-right: 8px;
}
.s-t-right {
  display: flex;
  justify-content: space-around;
  align-items: center;
  text-align: center;
  width: 15%;
  font-size: 12px;
}
.s-t-right .btn-change {
  flex: 1;
  margin-right: 5px;
  padding: 2px 5px;
  border-radius: 5px;
  border: 1px solid #ccc;
  cursor: pointer;
}
.btn-change:hover {
  background: #f5f5f5;
}
.s-t-right .more {
  padding: 2px 5px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

/* 楼层内容区域 */
.storey-content {
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
}
.s-c-item {
  width: 17%;
  margin-bottom: 25px;
  margin-left: 3%;
}
.item-title {
  height: 17%;
  margin-top: 5px;
  margin-bottom: 10px;
  font-size: 14px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
  color: #4d4d4d;
}
.item-pic {
  position: relative;
  width: 100%;
  border-radius: 5px;
  overflow: hidden;
  /* box-shadow: 0 0 5px rgba(0, 0, 0, 0.2); */
}
.item-pic img {
  display: block;
  width: 100%;
}
.item-pic .count {
  position: absolute;
  left: 0;
  bottom: 0;
  font-size: 12px;
  padding: 3px 5px;
  color: #fff;
  background-image: linear-gradient(
    to top,
    rgba(0, 0, 0, 0.6),
    rgba(119, 119, 119, 0.1)
  );
  width: 100%;
  /* 一行显示 */
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
}
.duration {
  position: absolute;
  right: 0;
  top: 0;
  padding: 3px 5px;
  color: #fff;
  background-image: linear-gradient(
    to left bottom,
    rgba(0, 0, 0, 0.6),
    rgba(255, 255, 255, 0.1)
  );
  border-bottom-left-radius: 8px;
}
.item-up {
  padding-left: 5px;
}
.item-up a {
  font-size: 12px;
  color: #999;
}
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>

/*<!-- bilibili样式 -->*/
.bili-video-card {
  position: relative;
  --title-font-size: 15px;
  --title-line-height: 22px;
  --subtitle-font-size: 13px;
  --subtitle-line-height: 17px;
  --info-margin-top: 8px;
  --avatar-size: 36px;
  --avatar-margin-right: 8px;
  --icon-size: 18px;
  --title-padding-right: 16px;
  --follow-icon-font-size: 12px;
  --follow-icon-line-height: 17px;
}

.bili-video-card__skeleton {
  pointer-events: none;
  user-select: none;
}

.bili-video-card__skeleton--cover {
  padding-top: 56.25%;
  border-radius: 6px;
}

.bili-video-card__skeleton--info {
  margin-top: var(--info-margin-top);
  display: -webkit-flex;
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;
}

.bili-video-card__skeleton--right {
  min-width: 0;
  flex: 1;
}

.bili-video-card__skeleton--text {
  margin-bottom: 4px;
  width: 94%;
  height: calc(var(--title-line-height) - 4px);
  border-radius: 4px;
}

.bili-video-card__skeleton--text.short {
  width: 72%;
}

.bili-video-card__skeleton--light {
  margin-top: 8px;
  width: 50%;
  height: var(--subtitle-line-height);
  border-radius: 4px;
}

.bili-video-card__wrap {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;
  width: 100%;
}

.bili-video-card .bili-video-card__image {
  position: relative;
  z-index: 1;
  border-radius: 6px;
}

.bili-video-card .bili-video-card__image--wrap {
  padding-top: 56.25%;
  border-radius: inherit;
  cursor: pointer;
}

.bili-watch-later {
  display: -webkit-flex;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  top: 8px;
  right: 8px;
  width: 28px;
  height: 28px;
  border-radius: 6px;
  cursor: pointer;
  background-color: rgba(33, 33, 33, .8);
  z-index: 9;
  transform: translateZ(0);
}

.bili-watch-later .bili-watch-later__icon {
  pointer-events: none;
  user-select: none;
  width: 22px;
  height: 22px;
  color: #fff;
}

.bili-watch-later .bili-watch-later__tip {
  pointer-events: none;
  user-select: none;
  position: absolute;
  bottom: -6px;
  right: -10px;
  transform: translateY(100%);
  font-size: 12px;
  color: #fff;
  border-radius: 4px;
  line-height: 18px;
  padding: 4px 8px;
  background-color: rgba(0, 0, 0, .8);
  white-space: nowrap;
}

.bili-video-card .bili-video-card__cover {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;
  overflow: hidden;
  width: 100%;
  height: 100%;
  border-radius: 6px;
  object-fit: contain;
}

.v-inline-player {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
  pointer-events: none;
  user-select: none;
  border-radius: inherit;
  opacity: 0;
  transition: opacity .2s linear;
  overflow: hidden;
}

.bili-video-card .bili-video-card__mask {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 2;
  width: 100%;
  height: 100%;
  opacity: 1;
  transition: all .2s linear .2s;
  pointer-events: none;
}

.bili-video-card .bili-video-card__info {
  margin-top: var(--info-margin-top);
  min-height: calc(var(--title-line-height) * 2 + var(--subtitle-line-height) + 4px);
  display: -webkit-flex;
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;
}


</style>
