# 项目说明
植物大战僵尸安卓版Cocos2d-Android
# 植物大战僵尸

大学时用来练手和学习的课设，最近整理了一下代码，主要是基于Android-Cocos2D框架进行开发的安卓游戏《植物大战僵尸》。主要是用于学习练手，所以没有严格遵循原版本进行开发。大部分植物和僵尸的功能都进行魔改，与原版差异较大。

![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/S81221-231006.jpg)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/S81221-231011.jpg)

# 更新日志周报

## 第一周

### 任务概述

1. 收集游戏所需要用到的素材文件，包含：图片、音效、游戏数据等。
2. 设计特效马甲，用于游戏的特效系统。
3. 增加游戏中一些细节特效的处理。如僵尸死亡效果，植物安放效果等。
4. 实现了游戏部分功能的界面设计。如，图鉴、长按卡片说明等。
5. 界面一些按钮的点击功能。如，返回、退出。

### 详细描述


- 使用PS对收集到的图片素材进行调整。因为素材均为网络上搜索，所以会有参差不齐质量不一的现象。
- 设计一个特效马甲Effect，继承自CCSprite。特效马甲自用来做视觉上的效果，创建时需传入特效播放动画的地址、特效生命周期、播放延迟数等参数。
- 僵尸死亡时，创建一个播放特效马甲分别播放僵尸死亡的动画效果。在Zombie类创建一个death()函数，传入一个参数设置僵尸死亡的状态，状态为0是普通死亡（倒地掉脑袋），状态为1是被烧毁的效果，后期炸弹和火焰伤害类的植物需要用到。
- 创建了AlmanacLayer类，用来呈现植物的图鉴。将所有的植物卡片图鉴都渲染出来，点击任选可以查看相对应的植物的详细说明。并且在CombatLayer中，选择卡片时，长按未选择的卡片可以小窗口查看卡片说明。
- 在新增的界面下添加返回按钮。

### 项目运行截图
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/1-1.jpeg)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/1-2.jpeg)

--

## 第二周

### 任务概述

1. 为植物樱桃炸弹添加自身技能的效果。
2. 设计一个全图单位组选取的方案，为后续一些特殊植物做准备。
3. 修改部分来源实验课上的代码，为后续一些操作做准备。
4. 添加新植物火爆辣椒和制作其技能

### 详细描述

- 在制作樱桃炸弹的过程中，发现了炸弹初步制作完成后也遇到一些问题。比如当前植物添加到CombatLine当中，只能对当前所在CombatLine的所有僵尸造成伤害，而不会影响到其他行的僵尸。
- 在CombatLayer中，为存放CombatLine的ArrayList设置Get函数，让其可以被其他类获取到。这样一来就可以获得到其他列上的资源。用其樱桃炸弹中，获取本行和其他行上的所有僵尸，再者使用勾股定理公式或者CGPoint自带的距离函数逐个计算距离，小于一定的范围内，便对僵尸造成伤害。
- 火爆辣椒比樱桃炸弹来的容易些，不需要获取到其他行的资源，只需要对自身所在行的僵尸进行伤害即可。

![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/2-1.jpeg)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/2-2.jpeg)

--

## 第三周

### 任务概述

1. 为植物食人花添加其技能和属性。
2. 为植物土豆雷添加触碰爆炸的效果。
3. 添加新的植物窝瓜
4. 在地图上添加小推车和其触发功能

### 详细描述

- 食人花主要分为三个状态,分别为：等待——咬人——咀嚼。等待过程中需要等僵尸靠近，咬人过程中主要为播放咬人的动画，咬人后进入咀嚼模式，等待一定的时间恢复成等待模式，继续等待下一个僵尸靠近。
- 土豆类也分为三个状态，分别为：破土前——破土后——爆炸，破土后的土豆雷就让第一个触碰到的僵尸引起爆炸，并且小范围伤害当前行上的僵尸。
- 窝瓜也分为三个状态，分别为：等待——跳跃——砸人。敌人一旦靠近，窝瓜会跳跃（CCMove），然后砸向敌人。
- 小推车能清理当前行上的所有僵尸，前提是有僵尸触碰到它了。
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/3-1.jpeg)

--
## 第四周

### 任务概述

1. 添加新的植物，双生向阳花
2. 添加新的植物，格林射手
3. 添加新的植物，三射手
4. 添加新的植物，巨型坚果墙
5. 添加新的植物，毁灭菇
6. 添加新的植物，火炬树
7. 实现铲子的功能，可以铲掉植物

### 详细描述

- 双生向阳花继承自原来的Sunflower类，重新构造方法，使其每次多生产一颗太阳出来。
- 格林射手继承自原来的Peashooter类，使用延迟的效果，让其延迟发射4发子弹。
- 三射手继承自原来的Peashooter类，可以当前行和上下两行的同时发射子弹。在制作过程中，三射手需要对其中上下两行的敌人进行攻击，而创建的子弹在原来的系统中只能对当前行的僵尸进行伤害，所以需要去更改选取函数。在原来的bulletHurtCompute()函数中去做改动。同时也得将三射手分和它生产出来的子弹别加入到上下两行的Plants数组中，才能触发其中一行出现僵尸时发射子弹的功能。再者还需要做边界的判断，比如第一行没有下行，第五行没有上行，都不对其创建子弹。
- 巨型坚果墙，巨型坚果墙继承自原来的坚果墙，比坚果墙拥有更厚的生命值，并且可以抵挡一些后续会添加进去的特殊移动方式的僵尸。
- 毁灭菇，由于素材短缺，这个植物将被设计成原创的技能，类似杨桃炸弹，范围性选取伤害多行僵尸。
- 火炬树可以将经过的Peashooter类植物的子弹全都化为火焰弹，并且增加火焰伤害。并且自身每隔10秒钟会对前方的僵尸进行火焰喷射的伤害。
- 铲子可以铲掉地图上已经种植的植物，不会占用到种植地的空间。

- 小推车能清理当前行上的所有僵尸，前提是有僵尸触碰到它了。


![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/4-1.jpeg)

--

## 第五周

### 任务概述

1. 制作全图太阳随机掉落的功能
2. 新增植物，分裂豌豆
3. 新增植物，地刺，地刺王
4. 新增植物，杨桃
5. 新增植物，大蒜
6. 设计游戏暂停页面
7. 设计游戏背景音乐和音效
8. 新增僵尸：橄榄球僵尸、读报僵尸

### 详细描述

- 使用random()随机数要设定随机0~4行和0~8列后，获取目标坐标。创建太阳使用CCMoveTo()函数移动到目标点。
- 分裂豌豆继承自原来Peashooter类，使其向后方再创建一颗子弹并移动它。
- 地刺和地刺王安放后会对经过的僵尸造成20点伤害，主要是为后续可能添加的使用交通工具的僵尸做准备。
- 杨桃的功能是需要对全图的僵尸进行选取，然后依次判断杨桃僵尸是否进入了杨桃五个角的锁定区当中，五个角有五个角度，每个角度所选取的扇形范围偏置角度大概为当前角度为基准的上下偏移10度左右。需要先拿到僵尸和杨桃的夹角，夹角计算采用数学公式反正切函数 “ang = Atan2(y0-y1/x0-x1)”（x0，y0为杨桃坐标，x1，y1为僵尸坐标）,但是这样拿到的ang值并不是为我们所熟悉的角度值(360度),而是取值范围小于1的弧度制。所以利用公式“ 角度 = 弧度 * 180/PI ”，得到夹角值(-180~180)。获取到夹角后，只需判断夹角是否有在锁定区角度范围内即可触发杨桃子弹。
- 新增植物大蒜，大蒜会把靠近的僵尸熏到上下其中一行。该设定需要将当前行僵尸组中的僵尸移除，并且将其添加到目标行，然后重新设置僵尸end目标点。
- 游戏暂停页面为PauseLayer()层，触发后添加在CombatLayer的双亲层底下，也就是和CombatLayer是兄弟层。暂停页面有返回主菜单、查看图鉴、开关音乐和音效等功能。在打开时，会将当前CombatLayer层暂停，点击回到游戏才能继续。
- 游戏背景音乐和音效采用SoundEngine作为播放引擎。游戏音效需要使用preloadEffect()函数进行预加载才可以正常播放，否则第一次播放音效会作为预加载而播放失败。
- 橄榄球僵尸和读报僵尸均继承自原来Zombie类，加强了生命力和移动速度的僵尸。

![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/5-1.jpeg)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/5-2.jpeg)

--

## 第六周

### 任务概述

1. 新增铁桶僵尸、路障僵尸
2. 新增撑杆跳僵尸并制作其技能
3. 新增关卡页面和设计关卡模式
4. 新增选择白天和黑夜模式功能
5. 设置关卡的进程进度条
6. 新增完成关卡后选择新的卡牌功能
7. 增加新的植物类——投手类
9. 增加新的植物——强化路灯
10. 修复一些bug

### 详细描述

- 铁桶僵尸和路障僵尸和原来的普通僵尸区别只在于生命值和播放的贴图不同，而且如果生命值低于设定的一个值情况下，两个僵尸都会变成原来的僵尸。所以需要在原来的Zombie类上进行拓展，增加两种新的僵尸的功能。
- 撑杆跳僵尸分为两种状态：有杆状态和无杆状态。有杆状态下会跳过一些植物，但是会被巨型坚果墙给阻挡。在完成跳跃后，会进入无杆状态，和普通僵尸无异。
- 新增选择关卡的页面，页面里可以选择自己想玩的关卡和关卡相关信息。
- 关切模式为创建新的类CheckPoint，类中的元素记录了关卡的信息，比如僵尸类型、僵尸数量、通过卡片等。
- 新增白天黑夜模式的选择功能，主要是为了后续可能加入白天和黑夜两种游戏模式做准备。选择白天和黑夜会得到不同的UI画面和战斗环境画面，但是暂时没有做其他的功能逻辑处理。
- 关卡进程进度条采用cocos2d自带的进度条类CCProgressTimer来进行设计和实现，当僵尸越来越多，进度条就越来越满。
- 当击败关卡最后一个僵尸时，当前关卡后会赠送一张新的植物卡片，点击即可进去详细图鉴页面AlongAlanLayer，页面中可以选择进入下一关。
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/6-1.jpeg)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/6-2.jpeg)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/6-3.jpeg)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/6-4.jpeg)


## 部分GIF截图
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/20.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/22.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/28.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/29.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/3.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/6.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/23.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/25.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/26.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/27.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/13.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/14.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/5.gif)
![avatar](https://github.com/tunm123/TUNMPVZ/blob/master/img/16.gif)

