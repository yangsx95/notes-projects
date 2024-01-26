// @author yangsx
// @date 2021.6.29
// Jonson At 2022.02.09 For 信贷改造对接
// Jonson At 2022.07.16 For 20220716_新信贷merge分支
node {

    parameters {
        choice choices: ['test_fp_framework', 'test_tp_framework', '20201231二期与生产分支合并', 'master', '20220113_新信贷对接', '20220222_新信贷SIT', '20220222_新信贷UAT', 'dev-20220530merge新信贷into新票据', '20220716_新信贷merge分支'], description: '选择打包分支', name: 'BRANCH_NAME'
        booleanParam defaultValue: false, description: '是否是全量打包', name: 'ALL_MODULES'
        text defaultValue: 'eweb,mca,router', description: '输入包名称，并且以英文逗号隔开，支持以下包：eweb,mca,router,mweb,mcm,user,auth,limit,batch,product,transfer', name: 'MODULE_NAMES'
    }

    def modulesPath = [];
    def moduleNames = [];
    def moduleJar = [];
    stage('准备') {
        echo "brach: ${params.BRANCH_NAME}"
        if (params.ALL_MODULES) {
            echo "modules : 全量包"
            moduleJar.add("${env.WORKSPACE}\\framework-web\\eweb\\eweb-starter\\target\\eweb-starter-0.0.1-SNAPSHOT.jar")
            moduleJar.add("${env.WORKSPACE}\\framework-mca\\mca-starter\\target\\mca-starter-0.0.1-SNAPSHOT.jar")
            moduleJar.add("${env.WORKSPACE}\\framework-web\\mweb\\mweb-starter\\target\\mweb-starter-0.0.1-SNAPSHOT.jar")
            moduleJar.add("${env.WORKSPACE}\\framework-mcm\\mcm-starter\\target\\mcm-starter-0.0.1-SNAPSHOT.jar")
            moduleJar.add("${env.WORKSPACE}\\framework-router\\router-starter\\target\\router-starter-0.0.1-SNAPSHOT.jar")
            moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\auth\\auth-starter\\target\\auth-starter-0.0.1-SNAPSHOT.jar")
            moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\batch\\batch-starter\\target\\batch-starter-0.0.1-SNAPSHOT.jar")
            moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\limit\\limit-starter\\target\\limit-starter-0.0.1-SNAPSHOT.jar")
            moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\points\\points-starter\\target\\points-starter-0.0.1-SNAPSHOT.jar")
            moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\product\\product-starter\\target\\product-starter-0.0.1-SNAPSHOT.jar")
            moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\transfer\\transfer-starter\\target\\transfer-starter-0.0.1-SNAPSHOT.jar")
            moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\user\\user-starter\\target\\user-starter-0.0.1-SNAPSHOT.jar")
        } else {
            moduleNames = params.MODULE_NAMES.split(",");
            for (module in moduleNames) {
                m = module.trim()
                if (!m) {
                    continue
                }
                switch (m) {
                    case "eweb":
                       modulesPath.add('framework-web/eweb/eweb-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-web\\eweb\\eweb-starter\\target\\eweb-starter-0.0.1-SNAPSHOT.jar")
                       break
                    case "mca":
                       modulesPath.add('framework-mca/mca-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-mca\\mca-starter\\target\\mca-starter-0.0.1-SNAPSHOT.jar")
                       break
                    case "mweb":
                       modulesPath.add('framework-web/mweb/mweb-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-web\\mweb\\mweb-starter\\target\\mweb-starter-0.0.1-SNAPSHOT.jar")
                       break
                    case "router":
                       modulesPath.add('framework-router/router-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-router\\router-starter\\target\\router-starter-0.0.1-SNAPSHOT.jar")
                       break
                    case "mcm":
                       modulesPath.add('framework-mcm/mcm-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-mcm\\mcm-starter\\target\\mcm-starter-0.0.1-SNAPSHOT.jar")
                       break
                    case "auth":
                       modulesPath.add('framework-micro-services/auth/auth-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\auth\\auth-starter\\target\\auth-starter-0.0.1-SNAPSHOT.jar")
                       break
                    case "batch":
                       modulesPath.add('framework-micro-services/batch/batch-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\batch\\batch-starter\\target\\batch-starter-0.0.1-SNAPSHOT.jar")
                       break
                    case "limit":
                       modulesPath.add('framework-micro-services/limit/limit-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\limit\\limit-starter\\target\\limit-starter-0.0.1-SNAPSHOT.jar")
                       break
                    case "points":
                       modulesPath.add('framework-micro-services/points/points-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\points\\points-starter\\target\\points-starter-0.0.1-SNAPSHOT.jar")
                       break
                    case "product":
                       modulesPath.add('framework-micro-services/product/product-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\product\\product-starter\\target\\product-starter-0.0.1-SNAPSHOT.jar")
                       break
                    case "transfer":
                       modulesPath.add('framework-micro-services/transfer/transfer-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\transfer\\transfer-starter\\target\\transfer-starter-0.0.1-SNAPSHOT.jar")
                       break
                    case "user":
                       modulesPath.add('framework-micro-services/user/user-starter')
                       moduleJar.add("${env.WORKSPACE}\\framework-micro-services\\user\\user-starter\\target\\user-starter-0.0.1-SNAPSHOT.jar")
                       break
                }
            }
            echo "modules : ${moduleNames}"
            echo "modules path: ${modulesPath}"
            echo "modules jar: ${moduleJar}"
        }
        echo "清除工作空间 ${env.WORKSPACE}"
        deleteDir()
    }

    stage('拉取代码') {
        echo "当前分支为: ${params.BRANCH_NAME}"
        git branch: params.BRANCH_NAME, credentialsId: '5ef24681-3a07-4b4d-9691-a9318d34c8c3', url: 'http://10.171.11.47/wjrcb/framework.git'
    }

    stage('maven打包') {
        if (params.ALL_MODULES) {
            bat 'mvn clean install package -DskipTests -Dmaven.repo.local=C:\\repository4sit'
        } else {
            for (module in modulesPath) {
                echo "---------- package ${module} -------"
                bat "mvn clean install -pl ${module}  -am -Pdev -Dmaven.test.skip=true -Dmaven.repo.local=C:\\repository4sit"
            }
        }
    }

    stage('移动jar') {
        bat "if not exist ${WORKSPACE}\\jars md ${WORKSPACE}\\jars"
        if (params.BRANCH_NAME.equals('master')) {
           for (jar in moduleJar) {
                bat "copy ${jar} E:\\plugins\\master\\"
           }
        } else {
            for (jar in moduleJar) {
                bat "copy ${jar} ${WORKSPACE}\\jars\\"
            }
        }
    }

    stage('上传jar') {
        if (params.BRANCH_NAME.equals('test_fp_framework')) {
            bat "cmd /k \"cd /d C:\\Program Files (x86)\\WinSCP&&winscp /console /command \"option batch continue\" \"option confirm off\" \"open sftp://csii:csii@10.171.51.91:22\" \"option transfer binary\" \"put ${WORKSPACE}\\jars\\* /home/csii/app/\" \"exit\" /log=log_file.txt"
        } else if (params.BRANCH_NAME.equals('test_tp_framework')) {
            bat "cmd /k \"cd /d C:\\Program Files (x86)\\WinSCP&&winscp /console /command \"option batch continue\" \"option confirm off\" \"open sftp://csii:csii@10.171.51.91:22\" \"option transfer binary\" \"put ${WORKSPACE}\\jars\\* /home/csii/app/phasetwo/\" \"exit\" /log=log_file.txt"
        } else if (params.BRANCH_NAME.equals('20201231二期与生产分支合并')) {
            bat "cmd /k \"cd /d C:\\Program Files (x86)\\WinSCP&&winscp /console /command \"option batch continue\" \"option confirm off\" \"open sftp://csii:csii@10.171.51.91:22\" \"option transfer binary\" \"put ${WORKSPACE}\\jars\\* /home/csii/app/phasetwo/phasetwoplgs/\" \"exit\" /log=log_file.txt"
        } else if (params.BRANCH_NAME.equals('20220113_新信贷对接')) {
            bat "cmd /k \"cd /d C:\\Program Files (x86)\\WinSCP&&winscp /console /command \"option batch continue\" \"option confirm off\" \"open sftp://csii:csii@10.171.51.91:22\" \"option transfer binary\" \"put ${WORKSPACE}\\jars\\* /home/csii/app/framework-xd/dev/\" \"exit\" /log=log_file.txt"
        } else if (params.BRANCH_NAME.equals('20220716_新信贷merge分支')) {
            bat "cmd /k \"cd /d C:\\Program Files (x86)\\WinSCP&&winscp /console /command \"option batch continue\" \"option confirm off\" \"open sftp://csii:csii@10.171.51.91:22\" \"option transfer binary\" \"put ${WORKSPACE}\\jars\\* /home/csii/app/framework-xd/dev/\" \"exit\" /log=log_file.txt"
        }else if (params.BRANCH_NAME.equals('20220222_新信贷SIT')) {
            bat "cmd /k \"cd /d C:\\Program Files (x86)\\WinSCP&&winscp /console /command \"option batch continue\" \"option confirm off\" \"open sftp://csii:csii@10.171.51.91:22\" \"option transfer binary\" \"put ${WORKSPACE}\\jars\\* /home/csii/app/framework-xd/sit/\" \"exit\" /log=log_file.txt"
        }else if (params.BRANCH_NAME.equals('20220222_新信贷UAT')) {
            bat "cmd /k \"cd /d C:\\Program Files (x86)\\WinSCP&&winscp /console /command \"option batch continue\" \"option confirm off\" \"open sftp://csii:csii@10.171.51.91:22\" \"option transfer binary\" \"put ${WORKSPACE}\\jars\\* /home/csii/app/framework-xduat/\" \"exit\" /log=log_file.txt"
        }else if (params.BRANCH_NAME.equals('dev-20220530merge新信贷into新票据')) {
            bat "cmd /k \"cd /d C:\\Program Files (x86)\\WinSCP&&winscp /console /command \"option batch continue\" \"option confirm off\" \"open sftp://csii:csii@10.171.51.91:22\" \"option transfer binary\" \"put ${WORKSPACE}\\jars\\* /home/csii/app/framework-xdpj/sit/\" \"exit\" /log=log_file.txt"
        }
    }

}