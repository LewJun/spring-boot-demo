package com.example.lewjun.web;

import com.example.lewjun.domain.Product;
import com.example.lewjun.domain.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/prod")
public class ProdController {

    @GetMapping("/province/{code}")
    public String province(@PathVariable final String code, final Model model) {
        log.info("【province code {}】", code);

        final String regionTitle = "四川省";
        model.addAttribute("regionTitle", regionTitle);

        model.addAttribute("path", "city");

        model.addAttribute("regions", Arrays.asList(
                new Region(510100, "成都市"),
                new Region(510300, "自贡市"),
                new Region(510400, "攀枝花市"),
                new Region(510500, "泸州市"),
                new Region(510600, "德阳市"),
                new Region(510700, "绵阳市"),
                new Region(510800, "广元市"),
                new Region(510900, "遂宁市"),
                new Region(511000, "内江市"),
                new Region(511100, "乐山市"),
                new Region(511300, "南充市"),
                new Region(511400, "眉山市"),
                new Region(511500, "宜宾市"),
                new Region(511600, "广安市"),
                new Region(511700, "达州市"),
                new Region(511800, "雅安市"),
                new Region(511900, "巴中市"),
                new Region(512000, "资阳市"),
                new Region(513200, "阿坝藏族羌族自治州"),
                new Region(513300, "甘孜藏族自治州"),
                new Region(513400, "凉山彝族自治州")
        ));

        model.addAttribute("prods", Arrays.asList(
                new Product(123, "金针菇", "kittens.jpg"),
                new Product(124, "芝麻", "kittens.jpg"),
                new Product(124, "芝麻2", "kittens.jpg"),
                new Product(124, "芝麻3", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg"),
                new Product(124, "芝麻4", "kittens.jpg")
        ));
        return "prod/prod.html";
    }

    @GetMapping("/city/{code}")
    public String city(@PathVariable final String code, final Model model) {
        log.info("【city code {}】", code);

        final String regionTitle = "成都市";
        model.addAttribute("regionTitle", regionTitle);

        model.addAttribute("path", "area");

        model.addAttribute("regions", Arrays.asList(
                new Region(234234, "武侯区"),
                new Region(224234, "青羊区"),
                new Region(23253, "郫县"),
                new Region(235345, "双流区"),
                new Region(255234, "金牛区")
        ));
        return "prod/prod.html";
    }


    @GetMapping("/area/{code}")
    public String area(@PathVariable final String code, final Model model) {
        log.info("【area code {}】", code);

        final String regionTitle = "武侯区";
        model.addAttribute("regionTitle", regionTitle);

        model.addAttribute("path", "area");

        model.addAttribute("regions", Arrays.asList(
                new Region(324234, "机投镇")
        ));

        return "prod/prod.html";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable final String id, final Model model) {
        model.addAttribute("title", "芝麻详情");

        model.addAttribute("html", "<blockquote>Web 富文本编辑器有很多，但我们是认真的！</blockquote>\n" +
                "    <h1 style=\"text-align: center\">为何选择 wangEditor</h1>\n" +
                "    <ul>\n" +
                "      <li>\n" +
                "        万星项目<a href=\"https://github.com/wangeditor-team/wangEditor/releases\"\n" +
                "          >Github Star 1w+</a\n" +
                "        >\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        简洁、轻量级、<a href=\"http://www.wangeditor.com/doc/\">文档</a>齐全\n" +
                "      </li>\n" +
                "      <li>QQ 群及时答疑</li>\n" +
                "      <li>\n" +
                "        <a\n" +
                "          href=\"http://www.wangeditor.com/doc/#%E5%BC%80%E5%8F%91%E4%BA%BA%E5%91%98\"\n" +
                "          >开源团队</a\n" +
                "        >维护，非个人单兵作战\n" +
                "      </li>\n" +
                "    </ul>\n" +
                "    <h1>初见</h1>\n" +
                "    <p>\n" +
                "      npm 安装<code>npm i wangeditor --save</code>，几行代码即可创建一个编辑器。\n" +
                "    </p>\n" +
                "    <pre><code>import E from 'wangeditor'\n" +
                "const editor = new E('#div1')\n" +
                "editor.create()</code></pre>\n" +
                "    <p>\n" +
                "      更多使用配置，请阅读<a href=\"http://www.wangeditor.com/doc/\">使用文档</a\n" +
                "      >。\n" +
                "    </p>\n" +
                "    <h1>浏览器兼容性</h1>\n" +
                "    <ul>\n" +
                "      <li>兼容主流 PC 浏览器，IE11+</li>\n" +
                "      <li>不支持移动端和 ipad</li>\n" +
                "    </ul>\n" +
                "    <h1>遇到问题</h1>\n" +
                "    <ul>\n" +
                "      <li>\n" +
                "        加入 QQ 群：<b>901247714</b>，164999061(人已满)，710646022(人已满)\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <a\n" +
                "          href=\"https://github.com/wangeditor-team/wangEditor/issues\"\n" +
                "          target=\"_blank\"\n" +
                "          >提交问题和建议</a\n" +
                "        >\n" +
                "      </li>\n" +
                "    </ul>\n" +
                "    <h1>贡献代码</h1>\n" +
                "    <p>\n" +
                "      欢迎非团队成员贡献代码，提交 Pull Request，请一定参考<a\n" +
                "        href=\"https://github.com/wangeditor-team/wangEditor/blob/master/docs/contribution.md\"\n" +
                "        target=\"_blank\"\n" +
                "        >贡献代码流程</a\n" +
                "      >。\n" +
                "    </p>\n" +
                "    <h1>谁在维护</h1>\n" +
                "    <p>\n" +
                "      wangEditor 现有一个开源团队在维护，团队可以保证答疑、bug 修复和迭代效率。\n" +
                "    </p>\n" +
                "    <p>\n" +
                "      <a\n" +
                "        href=\"http://www.wangeditor.com/doc/#%E5%BC%80%E5%8F%91%E4%BA%BA%E5%91%98\"\n" +
                "        >查看开发团队，或想加入开发团队</a\n" +
                "      >\n" +
                "    </p>\n" +
                "    <h1>为我们点赞</h1>\n" +
                "    <p>如果你感觉有收获，欢迎给我打赏，以激励我们更多输出优质开源内容。</p>\n" +
                "    <p>\n" +
                "      <img src=\"http://www.wangeditor.com/imgs/ali-pay.jpeg\" /><img\n" +
                "        src=\"http://www.wangeditor.com/imgs/wechat-pay.jpeg\"\n" +
                "      />\n" +
                "    </p>\n" +
                "    <p><br /><br /></p>");

        return "prod/detail.html";
    }

    @GetMapping("/list")
    public String list() {
        return "prod/list.html";
    }

    @GetMapping("/create")
    public String create() {
        return "prod/create.html";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request) {
        MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
        MultipartFile pic = mhsr.getFile("pic");
        String originalFilename = pic.getOriginalFilename();
        log.info("originalFilename {}", originalFilename);

        log.info("pic {}, {}", pic.getSize(), pic.getName());

        request.getParameterMap().forEach((s, strings) -> log.info("{}, {}", s, strings));

        return "ok";
    }
}
