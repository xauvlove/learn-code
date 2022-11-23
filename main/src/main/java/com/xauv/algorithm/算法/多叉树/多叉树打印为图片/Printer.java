package com.xauv.algorithm.算法.多叉树.多叉树打印为图片;

import com.xauv.algorithm.基本数据结构.图.ListGraph;
import net.sourceforge.plantuml.OptionFlags;
import net.sourceforge.plantuml.Run;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author: Bing Juan
 * @date: 2022/11/22 17 48
 * @desc:
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class Printer {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 生成树
        TreeNode<String> tree = TreeFactory.create();

        // 根据树生成图
        GraphGenerator<?> graphGenerator = new GraphGenerator<>();
        ListGraph graph = graphGenerator.createGraph(tree);

        // 生成待编译文件
        String path = PlantFileGenerator.genFilePath(graph);
        OptionFlags.getInstance().setSystemExit(false);

        // 进行编译
        Run.main(new String[]{path});

        // 打开文件
        Desktop.getDesktop().open(new File(path.replace("txt", "png")));
    }
}
