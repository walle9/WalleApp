package com.compose.walleapp.viewmodel

import androidx.lifecycle.ViewModel
import com.compose.walleapp.model.entity.ArticleEntity

/**
 * @author  walle
 * @date    2022/9/27
 * @desc    .
 */

class ArticleViewModel : ViewModel() {

    //文章数据列表
    var list = listOf(
        ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
        ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
        ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
        ArticleEntity("中国航天3天3连发", "澎湃新闻", "2022-09-27"),
        ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
        ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
        ArticleEntity("中国航天3天3连发", "澎湃新闻", "2022-09-27"),
        ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
        ArticleEntity("中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
        ArticleEntity("中国航天3天3连发", "澎湃新闻", "2022-09-27"),
        ArticleEntity("中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27")
    )
        private set

    //HTML头部
    private val htmlHeader = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title></title>
            <style>
                img {
                    max-width: 100% !important;
                }
            </style>
        </head>
        <body>
    """.trimIndent()

    //html尾部
    private val htmlFooter = """
        </body>
        </html>
    """.trimIndent()

    var content = """$htmlHeader
        <div class="index-module_articleWrap_2Zphx ">
    <div class="index-module_textWrap_3ygOc ">
        <p class="pgc-p" data-from-paste="1">
            这段时间，随着一个个西方国家宣布全面解禁，仿佛新冠疫情在西方已经成为过去时，但不要忘了，新冠病毒的传播力依然很强，致死率也远没有低到流感的级别，所以现在就放松警惕，并不是一个可取的办法。</p>
    </div>
    <div class="index-module_textWrap_3ygOc ">
        <p class="pgc-p" data-from-paste="1">
            还是以新冠第一大国美国为例，其3月份经常出现单日数千人染疫身亡的情况，而美国因为新冠疫情而死亡的总人数更是突破了百万，这无疑是个非常恐怖的数字。所以，哪怕美国不顾本国民众死活，任由疫情肆虐，我们也要把事情搞清楚，得知道病毒是怎么来的，这既是对生命的尊重，也是在还自己一个清白。
        </p>
        <p></p>
    </div>
    <div class="index-module_mediaWrap_213jB ">
        <div class="index-module_contentImg_JmmC0"><img
                src="https://pics3.baidu.com/feed/e4dde71190ef76c6dcccb2181cbaeef0af516719.png?token=564ba201f72b5d64b7e56fc655753278"
                width="640" class="index-module_large_1mscr">
        </div>
    </div>
    <div class="index-module_textWrap_3ygOc ">
        <p class="pgc-p" data-from-paste="1">
            特朗普和拜登两任美国总统都试图给我们扣黑帽子，想将自己防疫不力导致酿成恶果的责任推给我们，让中国无故蒙受了很多不实指责。但实际上，其一个劲地混淆视听，倒打一耙，恐怕还有另外一个令人震惊的原因。</p>
        <p></p>
    </div>
    <div class="index-module_textWrap_3ygOc ">
        <p class="pgc-p" data-from-paste="1">
            媒体3月24日报道称，英国《每日邮报》披露，一个病毒研究团队发现，新冠病毒的基因中居然含有莫德纳公司在2016年2月份申请的专利基因片段，而这一基因片段通过自然进化随机出现在新冠病毒中的可能性为三万亿分之一。换个角度思考，也就是说病毒不是莫德纳公司制造的几率仅有三万亿分之一左右。
        </p>
        <p></p>
    </div>
    <div class="index-module_mediaWrap_213jB ">
        <div class="index-module_contentImg_JmmC0"><img
                src="https://pics5.baidu.com/feed/5366d0160924ab18d3f0cd115a56f5c77a890b63.jpeg?token=3e61e67996f5e144f4548c7df24e99d5"
                width="640" class="index-module_large_1mscr">
        </div>
    </div>
    <div class="index-module_textWrap_3ygOc ">
        <p class="pgc-p" data-from-paste="1">
            如果忽略那么一丁点微乎其微的巧合，那这事几乎算是板上钉钉了，至于莫德纳的名字，想必大家都不陌生，该疫苗生产商在这场疫情中赚得是盆满钵满。事实上，近两年有关新冠病毒最早在美出现，甚至由美国制造的声音时有传出，部分外媒更是斩钉截铁地表示，病毒是在德特里克堡内研发出来的，然后经由美军士兵被带到欧洲，并列举了多项证据。
        </p>
        <p></p>
    </div>
    <div class="index-module_textWrap_3ygOc ">
        <p class="pgc-p" data-from-paste="1"><span data-fake="1" data-from-paste="1"
                data-diagnose-id="eb77731f1b9911bc00274ed204e4ef1b">虽然我们无法马上得出结论，</span><span id="typo-645"
                data-from-paste="1" data-diagnose-id="2ea906e135048b3c11b22356b8c7a8e5">认为</span><span data-fake="1"
                data-from-paste="1"
                data-diagnose-id="9e15e0362fb4d723e958644bde01e855">相关爆料一定是真实的，可上述指控正好解释了为什么欧洲会发现大量比武汉首批感染病例还要早上几个月的确诊案例，难道一切真那么巧吗。</span>
        </p>
        <p></p>
    </div>
    <div class="index-module_mediaWrap_213jB ">
        <div class="index-module_contentImg_JmmC0"><img
                src="https://pics6.baidu.com/feed/91529822720e0cf3681f7b1a64eae115bc09aa99.png?token=d585e49adc442abbc3a8e63550aabaf6"
                width="640" class="index-module_large_1mscr">
        </div>
    </div>
    <div class="index-module_textWrap_3ygOc ">
        <p class="pgc-p" data-from-paste="1">
            另外，本月早些时候，国内多家媒体纷纷转载了一条信息，在2019年11月份，武汉华南海鲜市场一家商户向美国进口了一批冰鲜大龙虾，随后该商户的3名员工陆续出现新冠感染症状，接触龙虾包装袋的周边多家商户的工作人员，也被证实是首批感染者。
        </p>
    </div>
    <div class="index-module_textWrap_3ygOc ">
        <p class="pgc-p" data-from-paste="1">
            再结合2019年夏季，在美国突然爆发的“电子烟肺炎”，以及新冠全球大流行之后，“电子烟肺炎”又神奇般自己消失的情况来看，我们有充分理由怀疑，这两者其实就是一个东西。俗话说得好，天网恢恢疏而不漏，只要干了坏事，总会露出马脚，这不美国在乌克兰的秘密就被抖出来了。
        </p>
        <p></p>
    </div>
    <div class="index-module_mediaWrap_213jB ">
        <div class="index-module_contentImg_JmmC0"><img
                src="https://pics5.baidu.com/feed/a08b87d6277f9e2fbb1ef25d719cfa2eb999f3f7.png?token=a28e912aa408cba7de369805417a26e1"
                width="640" class="index-module_large_1mscr">
        </div>
    </div>
    <div class="index-module_textWrap_3ygOc ">
        <p class="pgc-p" data-from-paste="1">
            俄军最近找到了大量证据，足以证实五角大楼曾在乌克兰境内设立的数十个实验室中制造化学武器，而且不排除一些部件已经被生产出来。除此之外，莫斯科还发现美国利用蝙蝠研究冠状病毒，那些被公开的文件上则有美国政府部门印章和官员的签名，这点华盛顿该如何解释？
        </p>
    </div>
    <div class="index-module_textWrap_3ygOc ">
        <p class="pgc-p" data-from-paste="1">
            然而我们还是低估了美国的厚脸皮，面对中俄共同在安理会施压，其直接矢口否认，且拒绝接受任何调查。只是在证据面前，任何狡辩都显得苍白无力，此前特朗普曾扬言，要就疫情向中国索赔10万亿美元，看来真正应该向世界赔偿10万亿美元的，恰恰是美国自己。
        </p>
    </div>
    <div class="index-module_mediaWrap_213jB">
        <div class="index-module_reportContainer_1LjGN"><span>举报/反馈</span></div>
    </div>
</div>${htmlFooter}
    """.trimIndent()


}