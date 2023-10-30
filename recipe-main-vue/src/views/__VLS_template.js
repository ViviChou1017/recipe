import { Delete, Star, Edit } from '@element-plus/icons-vue';
import { __VLS_internalComponent, __VLS_componentsOption, __VLS_name, recipeName, Images, dolike, uploader, doTrack, uploaderId, isWhiteStar, isBlackStar, rank, prepTime, introduction, ingredientName, ingredientWeight, step, allScore, memberAccounts, isShowDelete, signed, updateMessageAndScore, doDeleteScoreAndMessage, allMessage, score, content, createOrUpdateMessageAndScore, CancelCreate, isShowScoreAndMessage } from './SingleRecipe.vue';

function __VLS_template() {
let __VLS_ctx!: InstanceType<__VLS_PickNotAny<typeof __VLS_internalComponent, new () => {}>> & {};
/* Components */
let __VLS_otherComponents!: NonNullable<typeof __VLS_internalComponent extends { components: infer C; } ? C : {}> & typeof __VLS_componentsOption;
let __VLS_own!: __VLS_SelfComponent<typeof __VLS_name, typeof __VLS_internalComponent & (new () => { $slots: typeof __VLS_slots; })>;
let __VLS_localComponents!: typeof __VLS_otherComponents & Omit<typeof __VLS_own, keyof typeof __VLS_otherComponents>;
let __VLS_components!: typeof __VLS_localComponents & __VLS_GlobalComponents & typeof __VLS_ctx;
/* Style Scoped */
type __VLS_StyleScopedClasses = {} &
{ 'aaa'?: boolean; } &
{ 'card-title'?: boolean; } &
{ 'top-right-button'?: boolean; } &
{ 'top-right-button2'?: boolean; } &
{ 'infinite-list-wrapper'?: boolean; } &
{ 'submit-message'?: boolean; } &
{ 'el-card-d'?: boolean; } &
{ 'box'?: boolean; } &
{ 'box'?: boolean; } &
{ 'grid-content'?: boolean; };
let __VLS_styleScopedClasses!: __VLS_StyleScopedClasses | keyof __VLS_StyleScopedClasses | (keyof __VLS_StyleScopedClasses)[];
/* CSS variable injection */
/* CSS variable injection end */
let __VLS_resolvedLocalAndGlobalComponents!: {} &
__VLS_WithComponent<'ElRow', typeof __VLS_localComponents, "ElRow", "elRow", "el-row"> &
__VLS_WithComponent<'ElCol', typeof __VLS_localComponents, "ElCol", "elCol", "el-col"> &
__VLS_WithComponent<'ElButton', typeof __VLS_localComponents, "ElButton", "elButton", "el-button"> &
__VLS_WithComponent<'ElIcon', typeof __VLS_localComponents, "ElIcon", "elIcon", "el-icon"> &
__VLS_WithComponent<'Star', typeof __VLS_localComponents, "Star", "Star", "Star"> &
__VLS_WithComponent<'StarFilled', typeof __VLS_localComponents, "StarFilled", "StarFilled", "StarFilled"> &
__VLS_WithComponent<'ElCard', typeof __VLS_localComponents, "ElCard", "elCard", "el-card"> &
__VLS_WithComponent<'ElTimeline', typeof __VLS_localComponents, "ElTimeline", "elTimeline", "el-timeline"> &
__VLS_WithComponent<'ElTimelineItem', typeof __VLS_localComponents, "ElTimelineItem", "elTimelineItem", "el-timeline-item"> &
__VLS_WithComponent<'ElRate', typeof __VLS_localComponents, "ElRate", "elRate", "el-rate"> &
__VLS_WithComponent<'ElInput', typeof __VLS_localComponents, "ElInput", "elInput", "el-input"> &
__VLS_WithComponent<'ElAlert', typeof __VLS_localComponents, "ElAlert", "elAlert", "el-alert">;
({} as __VLS_IntrinsicElements).h1; ({} as __VLS_IntrinsicElements).h1;
__VLS_components.ElRow; __VLS_components.ElRow; __VLS_components.elRow; __VLS_components.elRow; __VLS_components["el-row"]; __VLS_components["el-row"];
// @ts-ignore
[ElRow, ElRow,];
__VLS_components.ElCol; __VLS_components.ElCol; __VLS_components.ElCol; __VLS_components.ElCol; __VLS_components.elCol; __VLS_components.elCol; __VLS_components.elCol; __VLS_components.elCol; __VLS_components["el-col"]; __VLS_components["el-col"]; __VLS_components["el-col"]; __VLS_components["el-col"];
// @ts-ignore
[ElCol, ElCol, ElCol, ElCol,];
({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div; ({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).img; ({} as __VLS_IntrinsicElements).img; ({} as __VLS_IntrinsicElements).img; ({} as __VLS_IntrinsicElements).img; ({} as __VLS_IntrinsicElements).img; ({} as __VLS_IntrinsicElements).img; ({} as __VLS_IntrinsicElements).img; ({} as __VLS_IntrinsicElements).img; ({} as __VLS_IntrinsicElements).img;
({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span; ({} as __VLS_IntrinsicElements).span;
({} as __VLS_IntrinsicElements).button; ({} as __VLS_IntrinsicElements).button;
({} as __VLS_IntrinsicElements).h5; ({} as __VLS_IntrinsicElements).h5; ({} as __VLS_IntrinsicElements).h5; ({} as __VLS_IntrinsicElements).h5;
__VLS_components.ElButton; __VLS_components.ElButton; __VLS_components.ElButton; __VLS_components.ElButton; __VLS_components.ElButton; __VLS_components.ElButton; __VLS_components.ElButton; __VLS_components.ElButton; __VLS_components.elButton; __VLS_components.elButton; __VLS_components.elButton; __VLS_components.elButton; __VLS_components.elButton; __VLS_components.elButton; __VLS_components.elButton; __VLS_components.elButton; __VLS_components["el-button"]; __VLS_components["el-button"]; __VLS_components["el-button"]; __VLS_components["el-button"]; __VLS_components["el-button"]; __VLS_components["el-button"]; __VLS_components["el-button"]; __VLS_components["el-button"];
// @ts-ignore
[ElButton, ElButton, ElButton, ElButton, ElButton, ElButton, ElButton, ElButton,];
__VLS_components.ElIcon; __VLS_components.ElIcon; __VLS_components.ElIcon; __VLS_components.ElIcon; __VLS_components.elIcon; __VLS_components.elIcon; __VLS_components.elIcon; __VLS_components.elIcon; __VLS_components["el-icon"]; __VLS_components["el-icon"]; __VLS_components["el-icon"]; __VLS_components["el-icon"];
// @ts-ignore
[ElIcon, ElIcon, ElIcon, ElIcon,];
__VLS_components.Star;
// @ts-ignore
[Star,];
__VLS_components.StarFilled;
// @ts-ignore
[StarFilled,];
({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p; ({} as __VLS_IntrinsicElements).p;
({} as __VLS_IntrinsicElements).h3; ({} as __VLS_IntrinsicElements).h3; ({} as __VLS_IntrinsicElements).h3; ({} as __VLS_IntrinsicElements).h3;
__VLS_components.ElCard; __VLS_components.ElCard; __VLS_components.ElCard; __VLS_components.ElCard; __VLS_components.elCard; __VLS_components.elCard; __VLS_components.elCard; __VLS_components.elCard; __VLS_components["el-card"]; __VLS_components["el-card"]; __VLS_components["el-card"]; __VLS_components["el-card"];
// @ts-ignore
[ElCard, ElCard, ElCard, ElCard,];
__VLS_components.ElTimeline; __VLS_components.ElTimeline; __VLS_components.elTimeline; __VLS_components.elTimeline; __VLS_components["el-timeline"]; __VLS_components["el-timeline"];
// @ts-ignore
[ElTimeline, ElTimeline,];
__VLS_components.ElTimelineItem; __VLS_components.ElTimelineItem; __VLS_components.elTimelineItem; __VLS_components.elTimelineItem; __VLS_components["el-timeline-item"]; __VLS_components["el-timeline-item"];
// @ts-ignore
[ElTimelineItem, ElTimelineItem,];
__VLS_components.ElRate; __VLS_components.ElRate; __VLS_components.elRate; __VLS_components.elRate; __VLS_components["el-rate"]; __VLS_components["el-rate"];
// @ts-ignore
[ElRate, ElRate,];
({} as __VLS_IntrinsicElements).a; ({} as __VLS_IntrinsicElements).a;
__VLS_components.ElInput; __VLS_components.ElInput; __VLS_components.elInput; __VLS_components.elInput; __VLS_components["el-input"]; __VLS_components["el-input"];
// @ts-ignore
[ElInput, ElInput,];
__VLS_components.ElAlert; __VLS_components.elAlert; __VLS_components["el-alert"];
// @ts-ignore
[ElAlert,];
{
const __VLS_0 = ({} as __VLS_IntrinsicElements)["h1"];
const __VLS_1 = __VLS_elementAsFunctionalComponent(__VLS_0);
({} as __VLS_IntrinsicElements).h1;
({} as __VLS_IntrinsicElements).h1;
const __VLS_2 = __VLS_1({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_1));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_0, typeof __VLS_2> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_3 = __VLS_pickFunctionalComponentCtx(__VLS_0, __VLS_2)!;
let __VLS_4!: __VLS_NormalizeEmits<typeof __VLS_3.emit>;
(__VLS_ctx.recipeName);
(__VLS_3.slots!).default;
}
{
let __VLS_5!: 'ElRow' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElRow : 'elRow' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elRow : 'el-row' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-row'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElRow'];
const __VLS_6 = __VLS_asFunctionalComponent(__VLS_5, new __VLS_5({ ...{}, gutter: ((24)), }));
({} as { ElRow: typeof __VLS_5; }).ElRow;
({} as { ElRow: typeof __VLS_5; }).ElRow;
const __VLS_7 = __VLS_6({ ...{}, gutter: ((24)), }, ...__VLS_functionalComponentArgsRest(__VLS_6));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_5, typeof __VLS_7> & Record<string, unknown>) => void)({ ...{}, gutter: ((24)), });
const __VLS_8 = __VLS_pickFunctionalComponentCtx(__VLS_5, __VLS_7)!;
let __VLS_9!: __VLS_NormalizeEmits<typeof __VLS_8.emit>;
{
let __VLS_10!: 'ElCol' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElCol : 'elCol' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elCol : 'el-col' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-col'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElCol'];
const __VLS_11 = __VLS_asFunctionalComponent(__VLS_10, new __VLS_10({ ...{}, span: ((23)), }));
({} as { ElCol: typeof __VLS_10; }).ElCol;
({} as { ElCol: typeof __VLS_10; }).ElCol;
const __VLS_12 = __VLS_11({ ...{}, span: ((23)), }, ...__VLS_functionalComponentArgsRest(__VLS_11));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_10, typeof __VLS_12> & Record<string, unknown>) => void)({ ...{}, span: ((23)), });
const __VLS_13 = __VLS_pickFunctionalComponentCtx(__VLS_10, __VLS_12)!;
let __VLS_14!: __VLS_NormalizeEmits<typeof __VLS_13.emit>;
{
const __VLS_15 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_16 = __VLS_elementAsFunctionalComponent(__VLS_15);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_17 = __VLS_16({ ...{}, class: ("card mb-3 left"), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_16));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_15, typeof __VLS_17> & Record<string, unknown>) => void)({ ...{}, class: ("card mb-3 left"), style: ({}), });
const __VLS_18 = __VLS_pickFunctionalComponentCtx(__VLS_15, __VLS_17)!;
let __VLS_19!: __VLS_NormalizeEmits<typeof __VLS_18.emit>;
for (const [img, index] of __VLS_getVForSourceType((__VLS_ctx.Images)!)) {
{
const __VLS_20 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_21 = __VLS_elementAsFunctionalComponent(__VLS_20);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_22 = __VLS_21({ ...{}, class: ("row g-0"), key: ((img.id)), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_21));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_20, typeof __VLS_22> & Record<string, unknown>) => void)({ ...{}, class: ("row g-0"), key: ((img.id)), style: ({}), });
const __VLS_23 = __VLS_pickFunctionalComponentCtx(__VLS_20, __VLS_22)!;
let __VLS_24!: __VLS_NormalizeEmits<typeof __VLS_23.emit>;
if (index === 0) {
{
const __VLS_25 = ({} as __VLS_IntrinsicElements)["img"];
const __VLS_26 = __VLS_elementAsFunctionalComponent(__VLS_25);
({} as __VLS_IntrinsicElements).img;
({} as __VLS_IntrinsicElements).img;
const __VLS_27 = __VLS_26({ ...{}, src: ((img.picBase64)), alt: ("..."), style: ({}), class: ("pic"), }, ...__VLS_functionalComponentArgsRest(__VLS_26));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_25, typeof __VLS_27> & Record<string, unknown>) => void)({ ...{}, src: ((img.picBase64)), alt: ("..."), style: ({}), class: ("pic"), });
const __VLS_28 = __VLS_pickFunctionalComponentCtx(__VLS_25, __VLS_27)!;
let __VLS_29!: __VLS_NormalizeEmits<typeof __VLS_28.emit>;
}
// @ts-ignore
[recipeName, Images,];
}
{
const __VLS_30 = ({} as __VLS_IntrinsicElements)["span"];
const __VLS_31 = __VLS_elementAsFunctionalComponent(__VLS_30);
({} as __VLS_IntrinsicElements).span;
({} as __VLS_IntrinsicElements).span;
const __VLS_32 = __VLS_31({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_31));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_30, typeof __VLS_32> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_33 = __VLS_pickFunctionalComponentCtx(__VLS_30, __VLS_32)!;
let __VLS_34!: __VLS_NormalizeEmits<typeof __VLS_33.emit>;
{
const __VLS_35 = ({} as __VLS_IntrinsicElements)["button"];
const __VLS_36 = __VLS_elementAsFunctionalComponent(__VLS_35);
({} as __VLS_IntrinsicElements).button;
({} as __VLS_IntrinsicElements).button;
const __VLS_37 = __VLS_36({ ...{ onClick: {} as any, }, style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_36));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_35, typeof __VLS_37> & Record<string, unknown>) => void)({ ...{ onClick: {} as any, }, style: ({}), });
const __VLS_38 = __VLS_pickFunctionalComponentCtx(__VLS_35, __VLS_37)!;
let __VLS_39!: __VLS_NormalizeEmits<typeof __VLS_38.emit>;
let __VLS_40 = { 'click': __VLS_pickEvent(__VLS_39['click'], ({} as __VLS_FunctionalComponentProps<typeof __VLS_36, typeof __VLS_37>).onClick) };
__VLS_40 = {
click: $event => {
__VLS_ctx.dolike();
}
};
{
const __VLS_41 = ({} as __VLS_IntrinsicElements)["h5"];
const __VLS_42 = __VLS_elementAsFunctionalComponent(__VLS_41);
({} as __VLS_IntrinsicElements).h5;
({} as __VLS_IntrinsicElements).h5;
const __VLS_43 = __VLS_42({ ...{}, class: ("card-title"), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_42));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_41, typeof __VLS_43> & Record<string, unknown>) => void)({ ...{}, class: ("card-title"), style: ({}), });
const __VLS_44 = __VLS_pickFunctionalComponentCtx(__VLS_41, __VLS_43)!;
let __VLS_45!: __VLS_NormalizeEmits<typeof __VLS_44.emit>;
{
const __VLS_46 = ({} as __VLS_IntrinsicElements)["span"];
const __VLS_47 = __VLS_elementAsFunctionalComponent(__VLS_46);
({} as __VLS_IntrinsicElements).span;
({} as __VLS_IntrinsicElements).span;
const __VLS_48 = __VLS_47({ ...{}, style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_47));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_46, typeof __VLS_48> & Record<string, unknown>) => void)({ ...{}, style: ({}), });
const __VLS_49 = __VLS_pickFunctionalComponentCtx(__VLS_46, __VLS_48)!;
let __VLS_50!: __VLS_NormalizeEmits<typeof __VLS_49.emit>;
(__VLS_ctx.recipeName);
(__VLS_49.slots!).default;
}
{
const __VLS_51 = ({} as __VLS_IntrinsicElements)["span"];
const __VLS_52 = __VLS_elementAsFunctionalComponent(__VLS_51);
({} as __VLS_IntrinsicElements).span;
({} as __VLS_IntrinsicElements).span;
const __VLS_53 = __VLS_52({ ...{}, style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_52));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_51, typeof __VLS_53> & Record<string, unknown>) => void)({ ...{}, style: ({}), });
const __VLS_54 = __VLS_pickFunctionalComponentCtx(__VLS_51, __VLS_53)!;
let __VLS_55!: __VLS_NormalizeEmits<typeof __VLS_54.emit>;
(__VLS_ctx.uploader);
(__VLS_54.slots!).default;
}
{
let __VLS_56!: 'ElButton' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElButton : 'elButton' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elButton : 'el-button' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-button'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElButton'];
const __VLS_57 = __VLS_asFunctionalComponent(__VLS_56, new __VLS_56({ ...{ onClick: {} as any, }, type: ("success"), style: ({}), }));
({} as { ElButton: typeof __VLS_56; }).ElButton;
({} as { ElButton: typeof __VLS_56; }).ElButton;
const __VLS_58 = __VLS_57({ ...{ onClick: {} as any, }, type: ("success"), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_57));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_56, typeof __VLS_58> & Record<string, unknown>) => void)({ ...{ onClick: {} as any, }, type: ("success"), style: ({}), });
const __VLS_59 = __VLS_pickFunctionalComponentCtx(__VLS_56, __VLS_58)!;
let __VLS_60!: __VLS_NormalizeEmits<typeof __VLS_59.emit>;
let __VLS_61 = { 'click': __VLS_pickEvent(__VLS_60['click'], ({} as __VLS_FunctionalComponentProps<typeof __VLS_57, typeof __VLS_58>).onClick) };
__VLS_61 = {
click: $event => {
__VLS_ctx.doTrack(__VLS_ctx.uploaderId);
}
};
(__VLS_59.slots!).default;
}
(__VLS_44.slots!).default;
}
{
const __VLS_62 = ({} as __VLS_IntrinsicElements)["span"];
const __VLS_63 = __VLS_elementAsFunctionalComponent(__VLS_62);
({} as __VLS_IntrinsicElements).span;
({} as __VLS_IntrinsicElements).span;
const __VLS_64 = __VLS_63({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_63));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_62, typeof __VLS_64> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_65 = __VLS_pickFunctionalComponentCtx(__VLS_62, __VLS_64)!;
let __VLS_66!: __VLS_NormalizeEmits<typeof __VLS_65.emit>;
{
let __VLS_67!: 'ElIcon' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElIcon : 'elIcon' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elIcon : 'el-icon' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-icon'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElIcon'];
const __VLS_68 = __VLS_asFunctionalComponent(__VLS_67, new __VLS_67({ ...{}, }));
({} as { ElIcon: typeof __VLS_67; }).ElIcon;
({} as { ElIcon: typeof __VLS_67; }).ElIcon;
const __VLS_69 = __VLS_68({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_68));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_67, typeof __VLS_69> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_70 = __VLS_pickFunctionalComponentCtx(__VLS_67, __VLS_69)!;
let __VLS_71!: __VLS_NormalizeEmits<typeof __VLS_70.emit>;
__VLS_directiveFunction(__VLS_ctx.vShow)((__VLS_ctx.isWhiteStar));
{
let __VLS_72!: 'Star' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.Star : (typeof __VLS_resolvedLocalAndGlobalComponents)['Star'];
const __VLS_73 = __VLS_asFunctionalComponent(__VLS_72, new __VLS_72({ ...{}, }));
({} as { Star: typeof __VLS_72; }).Star;
const __VLS_74 = __VLS_73({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_73));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_72, typeof __VLS_74> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_75 = __VLS_pickFunctionalComponentCtx(__VLS_72, __VLS_74)!;
let __VLS_76!: __VLS_NormalizeEmits<typeof __VLS_75.emit>;
}
(__VLS_70.slots!).default;
}
{
let __VLS_77!: 'ElIcon' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElIcon : 'elIcon' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elIcon : 'el-icon' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-icon'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElIcon'];
const __VLS_78 = __VLS_asFunctionalComponent(__VLS_77, new __VLS_77({ ...{}, }));
({} as { ElIcon: typeof __VLS_77; }).ElIcon;
({} as { ElIcon: typeof __VLS_77; }).ElIcon;
const __VLS_79 = __VLS_78({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_78));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_77, typeof __VLS_79> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_80 = __VLS_pickFunctionalComponentCtx(__VLS_77, __VLS_79)!;
let __VLS_81!: __VLS_NormalizeEmits<typeof __VLS_80.emit>;
__VLS_directiveFunction(__VLS_ctx.vShow)((__VLS_ctx.isBlackStar));
{
let __VLS_82!: 'StarFilled' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.StarFilled : (typeof __VLS_resolvedLocalAndGlobalComponents)['StarFilled'];
const __VLS_83 = __VLS_asFunctionalComponent(__VLS_82, new __VLS_82({ ...{}, }));
({} as { StarFilled: typeof __VLS_82; }).StarFilled;
const __VLS_84 = __VLS_83({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_83));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_82, typeof __VLS_84> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_85 = __VLS_pickFunctionalComponentCtx(__VLS_82, __VLS_84)!;
let __VLS_86!: __VLS_NormalizeEmits<typeof __VLS_85.emit>;
}
(__VLS_80.slots!).default;
}
(__VLS_65.slots!).default;
}
(__VLS_38.slots!).default;
}
(__VLS_33.slots!).default;
}
{
const __VLS_87 = ({} as __VLS_IntrinsicElements)["p"];
const __VLS_88 = __VLS_elementAsFunctionalComponent(__VLS_87);
({} as __VLS_IntrinsicElements).p;
({} as __VLS_IntrinsicElements).p;
const __VLS_89 = __VLS_88({ ...{}, class: ("card-title"), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_88));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_87, typeof __VLS_89> & Record<string, unknown>) => void)({ ...{}, class: ("card-title"), style: ({}), });
const __VLS_90 = __VLS_pickFunctionalComponentCtx(__VLS_87, __VLS_89)!;
let __VLS_91!: __VLS_NormalizeEmits<typeof __VLS_90.emit>;
(__VLS_ctx.rank);
(__VLS_90.slots!).default;
}
{
const __VLS_92 = ({} as __VLS_IntrinsicElements)["p"];
const __VLS_93 = __VLS_elementAsFunctionalComponent(__VLS_92);
({} as __VLS_IntrinsicElements).p;
({} as __VLS_IntrinsicElements).p;
const __VLS_94 = __VLS_93({ ...{}, class: ("card-title"), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_93));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_92, typeof __VLS_94> & Record<string, unknown>) => void)({ ...{}, class: ("card-title"), style: ({}), });
const __VLS_95 = __VLS_pickFunctionalComponentCtx(__VLS_92, __VLS_94)!;
let __VLS_96!: __VLS_NormalizeEmits<typeof __VLS_95.emit>;
(__VLS_ctx.prepTime);
(__VLS_95.slots!).default;
}
{
const __VLS_97 = ({} as __VLS_IntrinsicElements)["p"];
const __VLS_98 = __VLS_elementAsFunctionalComponent(__VLS_97);
({} as __VLS_IntrinsicElements).p;
({} as __VLS_IntrinsicElements).p;
const __VLS_99 = __VLS_98({ ...{}, class: ("card-title"), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_98));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_97, typeof __VLS_99> & Record<string, unknown>) => void)({ ...{}, class: ("card-title"), style: ({}), });
const __VLS_100 = __VLS_pickFunctionalComponentCtx(__VLS_97, __VLS_99)!;
let __VLS_101!: __VLS_NormalizeEmits<typeof __VLS_100.emit>;
(__VLS_ctx.introduction);
(__VLS_100.slots!).default;
}
(__VLS_23.slots!).default;
}
// @ts-ignore
[dolike, recipeName, uploader, doTrack, uploaderId, isWhiteStar, isBlackStar, rank, prepTime, introduction,];
}
(__VLS_18.slots!).default;
}
{
const __VLS_102 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_103 = __VLS_elementAsFunctionalComponent(__VLS_102);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_104 = __VLS_103({ ...{}, class: ("card mb-3"), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_103));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_102, typeof __VLS_104> & Record<string, unknown>) => void)({ ...{}, class: ("card mb-3"), style: ({}), });
const __VLS_105 = __VLS_pickFunctionalComponentCtx(__VLS_102, __VLS_104)!;
let __VLS_106!: __VLS_NormalizeEmits<typeof __VLS_105.emit>;
{
const __VLS_107 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_108 = __VLS_elementAsFunctionalComponent(__VLS_107);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_109 = __VLS_108({ ...{}, class: ("row g-0"), }, ...__VLS_functionalComponentArgsRest(__VLS_108));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_107, typeof __VLS_109> & Record<string, unknown>) => void)({ ...{}, class: ("row g-0"), });
const __VLS_110 = __VLS_pickFunctionalComponentCtx(__VLS_107, __VLS_109)!;
let __VLS_111!: __VLS_NormalizeEmits<typeof __VLS_110.emit>;
{
const __VLS_112 = ({} as __VLS_IntrinsicElements)["h3"];
const __VLS_113 = __VLS_elementAsFunctionalComponent(__VLS_112);
({} as __VLS_IntrinsicElements).h3;
({} as __VLS_IntrinsicElements).h3;
const __VLS_114 = __VLS_113({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_113));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_112, typeof __VLS_114> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_115 = __VLS_pickFunctionalComponentCtx(__VLS_112, __VLS_114)!;
let __VLS_116!: __VLS_NormalizeEmits<typeof __VLS_115.emit>;
(__VLS_115.slots!).default;
}
for (const [name, index] of __VLS_getVForSourceType((__VLS_ctx.ingredientName)!)) {
{
const __VLS_117 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_118 = __VLS_elementAsFunctionalComponent(__VLS_117);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_119 = __VLS_118({ ...{}, style: ({}), key: ((index)), }, ...__VLS_functionalComponentArgsRest(__VLS_118));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_117, typeof __VLS_119> & Record<string, unknown>) => void)({ ...{}, style: ({}), key: ((index)), });
const __VLS_120 = __VLS_pickFunctionalComponentCtx(__VLS_117, __VLS_119)!;
let __VLS_121!: __VLS_NormalizeEmits<typeof __VLS_120.emit>;
{
const __VLS_122 = ({} as __VLS_IntrinsicElements)["p"];
const __VLS_123 = __VLS_elementAsFunctionalComponent(__VLS_122);
({} as __VLS_IntrinsicElements).p;
({} as __VLS_IntrinsicElements).p;
const __VLS_124 = __VLS_123({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_123));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_122, typeof __VLS_124> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_125 = __VLS_pickFunctionalComponentCtx(__VLS_122, __VLS_124)!;
let __VLS_126!: __VLS_NormalizeEmits<typeof __VLS_125.emit>;
(name.ingredientName);
(__VLS_ctx.ingredientWeight[index]);
(__VLS_125.slots!).default;
}
(__VLS_120.slots!).default;
}
// @ts-ignore
[ingredientName, ingredientWeight,];
}
(__VLS_110.slots!).default;
}
(__VLS_105.slots!).default;
}
for (const [img, index] of __VLS_getVForSourceType((__VLS_ctx.Images.slice(1))!)) {
{
const __VLS_127 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_128 = __VLS_elementAsFunctionalComponent(__VLS_127);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_129 = __VLS_128({ ...{}, class: ("card mb-3"), style: ({}), key: ((img.id)), }, ...__VLS_functionalComponentArgsRest(__VLS_128));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_127, typeof __VLS_129> & Record<string, unknown>) => void)({ ...{}, class: ("card mb-3"), style: ({}), key: ((img.id)), });
const __VLS_130 = __VLS_pickFunctionalComponentCtx(__VLS_127, __VLS_129)!;
let __VLS_131!: __VLS_NormalizeEmits<typeof __VLS_130.emit>;
{
const __VLS_132 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_133 = __VLS_elementAsFunctionalComponent(__VLS_132);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_134 = __VLS_133({ ...{}, class: ("row g-0"), }, ...__VLS_functionalComponentArgsRest(__VLS_133));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_132, typeof __VLS_134> & Record<string, unknown>) => void)({ ...{}, class: ("row g-0"), });
const __VLS_135 = __VLS_pickFunctionalComponentCtx(__VLS_132, __VLS_134)!;
let __VLS_136!: __VLS_NormalizeEmits<typeof __VLS_135.emit>;
{
const __VLS_137 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_138 = __VLS_elementAsFunctionalComponent(__VLS_137);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_139 = __VLS_138({ ...{}, class: ("col-md-4"), }, ...__VLS_functionalComponentArgsRest(__VLS_138));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_137, typeof __VLS_139> & Record<string, unknown>) => void)({ ...{}, class: ("col-md-4"), });
const __VLS_140 = __VLS_pickFunctionalComponentCtx(__VLS_137, __VLS_139)!;
let __VLS_141!: __VLS_NormalizeEmits<typeof __VLS_140.emit>;
{
const __VLS_142 = ({} as __VLS_IntrinsicElements)["img"];
const __VLS_143 = __VLS_elementAsFunctionalComponent(__VLS_142);
({} as __VLS_IntrinsicElements).img;
({} as __VLS_IntrinsicElements).img;
const __VLS_144 = __VLS_143({ ...{}, src: ((img.picBase64)), alt: (""), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_143));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_142, typeof __VLS_144> & Record<string, unknown>) => void)({ ...{}, src: ((img.picBase64)), alt: (""), style: ({}), });
const __VLS_145 = __VLS_pickFunctionalComponentCtx(__VLS_142, __VLS_144)!;
let __VLS_146!: __VLS_NormalizeEmits<typeof __VLS_145.emit>;
}
(__VLS_140.slots!).default;
}
{
const __VLS_147 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_148 = __VLS_elementAsFunctionalComponent(__VLS_147);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_149 = __VLS_148({ ...{}, class: ("col-md-8"), }, ...__VLS_functionalComponentArgsRest(__VLS_148));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_147, typeof __VLS_149> & Record<string, unknown>) => void)({ ...{}, class: ("col-md-8"), });
const __VLS_150 = __VLS_pickFunctionalComponentCtx(__VLS_147, __VLS_149)!;
let __VLS_151!: __VLS_NormalizeEmits<typeof __VLS_150.emit>;
{
const __VLS_152 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_153 = __VLS_elementAsFunctionalComponent(__VLS_152);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_154 = __VLS_153({ ...{}, class: ("card-body"), }, ...__VLS_functionalComponentArgsRest(__VLS_153));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_152, typeof __VLS_154> & Record<string, unknown>) => void)({ ...{}, class: ("card-body"), });
const __VLS_155 = __VLS_pickFunctionalComponentCtx(__VLS_152, __VLS_154)!;
let __VLS_156!: __VLS_NormalizeEmits<typeof __VLS_155.emit>;
{
const __VLS_157 = ({} as __VLS_IntrinsicElements)["h5"];
const __VLS_158 = __VLS_elementAsFunctionalComponent(__VLS_157);
({} as __VLS_IntrinsicElements).h5;
({} as __VLS_IntrinsicElements).h5;
const __VLS_159 = __VLS_158({ ...{}, class: ("card-title"), }, ...__VLS_functionalComponentArgsRest(__VLS_158));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_157, typeof __VLS_159> & Record<string, unknown>) => void)({ ...{}, class: ("card-title"), });
const __VLS_160 = __VLS_pickFunctionalComponentCtx(__VLS_157, __VLS_159)!;
let __VLS_161!: __VLS_NormalizeEmits<typeof __VLS_160.emit>;
{
const __VLS_162 = ({} as __VLS_IntrinsicElements)["p"];
const __VLS_163 = __VLS_elementAsFunctionalComponent(__VLS_162);
({} as __VLS_IntrinsicElements).p;
({} as __VLS_IntrinsicElements).p;
const __VLS_164 = __VLS_163({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_163));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_162, typeof __VLS_164> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_165 = __VLS_pickFunctionalComponentCtx(__VLS_162, __VLS_164)!;
let __VLS_166!: __VLS_NormalizeEmits<typeof __VLS_165.emit>;
(index + 1);
(__VLS_165.slots!).default;
}
(__VLS_ctx.step[index].introduction);
(__VLS_160.slots!).default;
}
(__VLS_155.slots!).default;
}
(__VLS_150.slots!).default;
}
(__VLS_135.slots!).default;
}
(__VLS_130.slots!).default;
}
// @ts-ignore
[Images, step,];
}
(__VLS_13.slots!).default;
}
{
let __VLS_167!: 'ElCol' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElCol : 'elCol' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elCol : 'el-col' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-col'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElCol'];
const __VLS_168 = __VLS_asFunctionalComponent(__VLS_167, new __VLS_167({ ...{}, span: ((1)), style: ({}), }));
({} as { ElCol: typeof __VLS_167; }).ElCol;
({} as { ElCol: typeof __VLS_167; }).ElCol;
const __VLS_169 = __VLS_168({ ...{}, span: ((1)), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_168));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_167, typeof __VLS_169> & Record<string, unknown>) => void)({ ...{}, span: ((1)), style: ({}), });
const __VLS_170 = __VLS_pickFunctionalComponentCtx(__VLS_167, __VLS_169)!;
let __VLS_171!: __VLS_NormalizeEmits<typeof __VLS_170.emit>;
{
const __VLS_172 = ({} as __VLS_IntrinsicElements)["img"];
const __VLS_173 = __VLS_elementAsFunctionalComponent(__VLS_172);
({} as __VLS_IntrinsicElements).img;
({} as __VLS_IntrinsicElements).img;
const __VLS_174 = __VLS_173({ ...{}, style: ({}), src: ("https://img95.699pic.com/photo/40164/5434.gif_wh860.gif"), }, ...__VLS_functionalComponentArgsRest(__VLS_173));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_172, typeof __VLS_174> & Record<string, unknown>) => void)({ ...{}, style: ({}), src: ("https://img95.699pic.com/photo/40164/5434.gif_wh860.gif"), });
const __VLS_175 = __VLS_pickFunctionalComponentCtx(__VLS_172, __VLS_174)!;
let __VLS_176!: __VLS_NormalizeEmits<typeof __VLS_175.emit>;
}
{
const __VLS_177 = ({} as __VLS_IntrinsicElements)["img"];
const __VLS_178 = __VLS_elementAsFunctionalComponent(__VLS_177);
({} as __VLS_IntrinsicElements).img;
({} as __VLS_IntrinsicElements).img;
const __VLS_179 = __VLS_178({ ...{}, style: ({}), src: ("https://img95.699pic.com/photo/40173/9661.gif_wh300.gif"), }, ...__VLS_functionalComponentArgsRest(__VLS_178));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_177, typeof __VLS_179> & Record<string, unknown>) => void)({ ...{}, style: ({}), src: ("https://img95.699pic.com/photo/40173/9661.gif_wh300.gif"), });
const __VLS_180 = __VLS_pickFunctionalComponentCtx(__VLS_177, __VLS_179)!;
let __VLS_181!: __VLS_NormalizeEmits<typeof __VLS_180.emit>;
}
{
const __VLS_182 = ({} as __VLS_IntrinsicElements)["img"];
const __VLS_183 = __VLS_elementAsFunctionalComponent(__VLS_182);
({} as __VLS_IntrinsicElements).img;
const __VLS_184 = __VLS_183({ ...{}, style: ({}), src: ("https://cdn.store-assets.com/s/836670/f/10947278.jpg"), }, ...__VLS_functionalComponentArgsRest(__VLS_183));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_182, typeof __VLS_184> & Record<string, unknown>) => void)({ ...{}, style: ({}), src: ("https://cdn.store-assets.com/s/836670/f/10947278.jpg"), });
const __VLS_185 = __VLS_pickFunctionalComponentCtx(__VLS_182, __VLS_184)!;
let __VLS_186!: __VLS_NormalizeEmits<typeof __VLS_185.emit>;
}
(__VLS_170.slots!).default;
}
(__VLS_8.slots!).default;
}
{
let __VLS_187!: 'ElCard' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElCard : 'elCard' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elCard : 'el-card' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-card'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElCard'];
const __VLS_188 = __VLS_asFunctionalComponent(__VLS_187, new __VLS_187({ ...{}, class: ("el-card-d"), shadow: ("always"), style: ({}), }));
({} as { ElCard: typeof __VLS_187; }).ElCard;
({} as { ElCard: typeof __VLS_187; }).ElCard;
const __VLS_189 = __VLS_188({ ...{}, class: ("el-card-d"), shadow: ("always"), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_188));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_187, typeof __VLS_189> & Record<string, unknown>) => void)({ ...{}, class: ("el-card-d"), shadow: ("always"), style: ({}), });
const __VLS_190 = __VLS_pickFunctionalComponentCtx(__VLS_187, __VLS_189)!;
let __VLS_191!: __VLS_NormalizeEmits<typeof __VLS_190.emit>;
{
const __VLS_192 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_193 = __VLS_elementAsFunctionalComponent(__VLS_192);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_194 = __VLS_193({ ...{}, class: ("infinite-list-wrapper"), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_193));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_192, typeof __VLS_194> & Record<string, unknown>) => void)({ ...{}, class: ("infinite-list-wrapper"), style: ({}), });
const __VLS_195 = __VLS_pickFunctionalComponentCtx(__VLS_192, __VLS_194)!;
let __VLS_196!: __VLS_NormalizeEmits<typeof __VLS_195.emit>;
{
let __VLS_197!: 'ElTimeline' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElTimeline : 'elTimeline' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elTimeline : 'el-timeline' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-timeline'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElTimeline'];
const __VLS_198 = __VLS_asFunctionalComponent(__VLS_197, new __VLS_197({ ...{}, infiniteScrollDisabled: ("disabled"), }));
({} as { ElTimeline: typeof __VLS_197; }).ElTimeline;
({} as { ElTimeline: typeof __VLS_197; }).ElTimeline;
const __VLS_199 = __VLS_198({ ...{}, infiniteScrollDisabled: ("disabled"), }, ...__VLS_functionalComponentArgsRest(__VLS_198));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_197, typeof __VLS_199> & Record<string, unknown>) => void)({ ...{}, infiniteScrollDisabled: ("disabled"), });
const __VLS_200 = __VLS_pickFunctionalComponentCtx(__VLS_197, __VLS_199)!;
let __VLS_201!: __VLS_NormalizeEmits<typeof __VLS_200.emit>;
{
const __VLS_202 = ({} as __VLS_IntrinsicElements)["h3"];
const __VLS_203 = __VLS_elementAsFunctionalComponent(__VLS_202);
({} as __VLS_IntrinsicElements).h3;
({} as __VLS_IntrinsicElements).h3;
const __VLS_204 = __VLS_203({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_203));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_202, typeof __VLS_204> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_205 = __VLS_pickFunctionalComponentCtx(__VLS_202, __VLS_204)!;
let __VLS_206!: __VLS_NormalizeEmits<typeof __VLS_205.emit>;
(__VLS_205.slots!).default;
}
{
const __VLS_207 = ({} as __VLS_IntrinsicElements)["p"];
const __VLS_208 = __VLS_elementAsFunctionalComponent(__VLS_207);
({} as __VLS_IntrinsicElements).p;
({} as __VLS_IntrinsicElements).p;
const __VLS_209 = __VLS_208({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_208));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_207, typeof __VLS_209> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_210 = __VLS_pickFunctionalComponentCtx(__VLS_207, __VLS_209)!;
let __VLS_211!: __VLS_NormalizeEmits<typeof __VLS_210.emit>;
}
for (const [scoree, index] of __VLS_getVForSourceType((__VLS_ctx.allScore)!)) {
{
const __VLS_212 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_213 = __VLS_elementAsFunctionalComponent(__VLS_212);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_214 = __VLS_213({ ...{}, key: ((scoree.ratNumber)), }, ...__VLS_functionalComponentArgsRest(__VLS_213));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_212, typeof __VLS_214> & Record<string, unknown>) => void)({ ...{}, key: ((scoree.ratNumber)), });
const __VLS_215 = __VLS_pickFunctionalComponentCtx(__VLS_212, __VLS_214)!;
let __VLS_216!: __VLS_NormalizeEmits<typeof __VLS_215.emit>;
{
let __VLS_217!: 'ElTimelineItem' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElTimelineItem : 'elTimelineItem' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elTimelineItem : 'el-timeline-item' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-timeline-item'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElTimelineItem'];
const __VLS_218 = __VLS_asFunctionalComponent(__VLS_217, new __VLS_217({ ...{}, }));
({} as { ElTimelineItem: typeof __VLS_217; }).ElTimelineItem;
({} as { ElTimelineItem: typeof __VLS_217; }).ElTimelineItem;
const __VLS_219 = __VLS_218({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_218));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_217, typeof __VLS_219> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_220 = __VLS_pickFunctionalComponentCtx(__VLS_217, __VLS_219)!;
let __VLS_221!: __VLS_NormalizeEmits<typeof __VLS_220.emit>;
{
let __VLS_222!: 'ElCard' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElCard : 'elCard' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elCard : 'el-card' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-card'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElCard'];
const __VLS_223 = __VLS_asFunctionalComponent(__VLS_222, new __VLS_222({ ...{}, class: ("el-card-m"), style: ({}), }));
({} as { ElCard: typeof __VLS_222; }).ElCard;
({} as { ElCard: typeof __VLS_222; }).ElCard;
const __VLS_224 = __VLS_223({ ...{}, class: ("el-card-m"), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_223));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_222, typeof __VLS_224> & Record<string, unknown>) => void)({ ...{}, class: ("el-card-m"), style: ({}), });
const __VLS_225 = __VLS_pickFunctionalComponentCtx(__VLS_222, __VLS_224)!;
let __VLS_226!: __VLS_NormalizeEmits<typeof __VLS_225.emit>;
{
const __VLS_227 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_228 = __VLS_elementAsFunctionalComponent(__VLS_227);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_229 = __VLS_228({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_228));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_227, typeof __VLS_229> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_230 = __VLS_pickFunctionalComponentCtx(__VLS_227, __VLS_229)!;
let __VLS_231!: __VLS_NormalizeEmits<typeof __VLS_230.emit>;
{
const __VLS_232 = ({} as __VLS_IntrinsicElements)["span"];
const __VLS_233 = __VLS_elementAsFunctionalComponent(__VLS_232);
({} as __VLS_IntrinsicElements).span;
({} as __VLS_IntrinsicElements).span;
const __VLS_234 = __VLS_233({ ...{}, class: ("demonstration"), }, ...__VLS_functionalComponentArgsRest(__VLS_233));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_232, typeof __VLS_234> & Record<string, unknown>) => void)({ ...{}, class: ("demonstration"), });
const __VLS_235 = __VLS_pickFunctionalComponentCtx(__VLS_232, __VLS_234)!;
let __VLS_236!: __VLS_NormalizeEmits<typeof __VLS_235.emit>;
}
{
let __VLS_237!: 'ElRate' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElRate : 'elRate' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elRate : 'el-rate' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-rate'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElRate'];
const __VLS_238 = __VLS_asFunctionalComponent(__VLS_237, new __VLS_237({ ...{}, modelValue: ((scoree.score)), }));
({} as { ElRate: typeof __VLS_237; }).ElRate;
const __VLS_239 = __VLS_238({ ...{}, modelValue: ((scoree.score)), }, ...__VLS_functionalComponentArgsRest(__VLS_238));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_237, typeof __VLS_239> & Record<string, unknown>) => void)({ ...{}, modelValue: ((scoree.score)), });
const __VLS_240 = __VLS_pickFunctionalComponentCtx(__VLS_237, __VLS_239)!;
let __VLS_241!: __VLS_NormalizeEmits<typeof __VLS_240.emit>;
}
{
const __VLS_242 = ({} as __VLS_IntrinsicElements)["span"];
const __VLS_243 = __VLS_elementAsFunctionalComponent(__VLS_242);
({} as __VLS_IntrinsicElements).span;
({} as __VLS_IntrinsicElements).span;
const __VLS_244 = __VLS_243({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_243));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_242, typeof __VLS_244> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_245 = __VLS_pickFunctionalComponentCtx(__VLS_242, __VLS_244)!;
let __VLS_246!: __VLS_NormalizeEmits<typeof __VLS_245.emit>;
(__VLS_ctx.memberAccounts[scoree.raterNumber]);
(scoree.ratDate);
{
const __VLS_247 = ({} as __VLS_IntrinsicElements)["a"];
const __VLS_248 = __VLS_elementAsFunctionalComponent(__VLS_247);
({} as __VLS_IntrinsicElements).a;
({} as __VLS_IntrinsicElements).a;
const __VLS_249 = __VLS_248({ ...{}, href: ("#targetElementId"), }, ...__VLS_functionalComponentArgsRest(__VLS_248));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_247, typeof __VLS_249> & Record<string, unknown>) => void)({ ...{}, href: ("#targetElementId"), });
const __VLS_250 = __VLS_pickFunctionalComponentCtx(__VLS_247, __VLS_249)!;
let __VLS_251!: __VLS_NormalizeEmits<typeof __VLS_250.emit>;
if (__VLS_ctx.isShowDelete && scoree.raterNumber === __VLS_ctx.signed.memberNumber) {
{
let __VLS_252!: 'ElButton' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElButton : 'elButton' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elButton : 'el-button' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-button'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElButton'];
const __VLS_253 = __VLS_asFunctionalComponent(__VLS_252, new __VLS_252({ ...{ onClick: {} as any, }, type: ("primary"), icon: ((__VLS_ctx.Edit)), circle: (true), class: ("top-right-button"), }));
({} as { ElButton: typeof __VLS_252; }).ElButton;
const __VLS_254 = __VLS_253({ ...{ onClick: {} as any, }, type: ("primary"), icon: ((__VLS_ctx.Edit)), circle: (true), class: ("top-right-button"), }, ...__VLS_functionalComponentArgsRest(__VLS_253));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_252, typeof __VLS_254> & Record<string, unknown>) => void)({ ...{ onClick: {} as any, }, type: ("primary"), icon: ((__VLS_ctx.Edit)), circle: (true), class: ("top-right-button"), });
const __VLS_255 = __VLS_pickFunctionalComponentCtx(__VLS_252, __VLS_254)!;
let __VLS_256!: __VLS_NormalizeEmits<typeof __VLS_255.emit>;
let __VLS_257 = { 'click': __VLS_pickEvent(__VLS_256['click'], ({} as __VLS_FunctionalComponentProps<typeof __VLS_253, typeof __VLS_254>).onClick) };
__VLS_257 = {
click: $event => {
if (!((__VLS_ctx.isShowDelete && scoree.raterNumber === __VLS_ctx.signed.memberNumber))) return;
__VLS_ctx.updateMessageAndScore(scoree.ratNumber);
}
};
}
// @ts-ignore
[allScore, memberAccounts, isShowDelete, signed, Edit, Edit, Edit, updateMessageAndScore,];
}
(__VLS_250.slots!).default;
}
if (__VLS_ctx.isShowDelete && scoree.raterNumber === __VLS_ctx.signed.memberNumber) {
{
let __VLS_258!: 'ElButton' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElButton : 'elButton' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elButton : 'el-button' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-button'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElButton'];
const __VLS_259 = __VLS_asFunctionalComponent(__VLS_258, new __VLS_258({ ...{ onClick: {} as any, }, type: ("danger"), icon: ((__VLS_ctx.Delete)), circle: (true), class: ("top-right-button2"), }));
({} as { ElButton: typeof __VLS_258; }).ElButton;
const __VLS_260 = __VLS_259({ ...{ onClick: {} as any, }, type: ("danger"), icon: ((__VLS_ctx.Delete)), circle: (true), class: ("top-right-button2"), }, ...__VLS_functionalComponentArgsRest(__VLS_259));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_258, typeof __VLS_260> & Record<string, unknown>) => void)({ ...{ onClick: {} as any, }, type: ("danger"), icon: ((__VLS_ctx.Delete)), circle: (true), class: ("top-right-button2"), });
const __VLS_261 = __VLS_pickFunctionalComponentCtx(__VLS_258, __VLS_260)!;
let __VLS_262!: __VLS_NormalizeEmits<typeof __VLS_261.emit>;
let __VLS_263 = { 'click': __VLS_pickEvent(__VLS_262['click'], ({} as __VLS_FunctionalComponentProps<typeof __VLS_259, typeof __VLS_260>).onClick) };
__VLS_263 = {
click: $event => {
if (!((__VLS_ctx.isShowDelete && scoree.raterNumber === __VLS_ctx.signed.memberNumber))) return;
__VLS_ctx.doDeleteScoreAndMessage(scoree.ratNumber);
}
};
}
// @ts-ignore
[isShowDelete, signed, Delete, Delete, Delete, doDeleteScoreAndMessage,];
}
(__VLS_245.slots!).default;
}
for (const [message, index] of __VLS_getVForSourceType((__VLS_ctx.allMessage)!)) {
{
const __VLS_264 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_265 = __VLS_elementAsFunctionalComponent(__VLS_264);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_266 = __VLS_265({ ...{}, key: ((message.messageId)), }, ...__VLS_functionalComponentArgsRest(__VLS_265));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_264, typeof __VLS_266> & Record<string, unknown>) => void)({ ...{}, key: ((message.messageId)), });
const __VLS_267 = __VLS_pickFunctionalComponentCtx(__VLS_264, __VLS_266)!;
let __VLS_268!: __VLS_NormalizeEmits<typeof __VLS_267.emit>;
if (scoree.raterNumber === message.id) {
{
const __VLS_269 = ({} as __VLS_IntrinsicElements)["p"];
const __VLS_270 = __VLS_elementAsFunctionalComponent(__VLS_269);
({} as __VLS_IntrinsicElements).p;
({} as __VLS_IntrinsicElements).p;
const __VLS_271 = __VLS_270({ ...{}, }, ...__VLS_functionalComponentArgsRest(__VLS_270));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_269, typeof __VLS_271> & Record<string, unknown>) => void)({ ...{}, });
const __VLS_272 = __VLS_pickFunctionalComponentCtx(__VLS_269, __VLS_271)!;
let __VLS_273!: __VLS_NormalizeEmits<typeof __VLS_272.emit>;
(message.content);
(__VLS_272.slots!).default;
}
// @ts-ignore
[allMessage,];
}
(__VLS_267.slots!).default;
}
}
(__VLS_230.slots!).default;
}
(__VLS_225.slots!).default;
}
(__VLS_220.slots!).default;
}
(__VLS_215.slots!).default;
}
}
(__VLS_200.slots!).default;
}
(__VLS_195.slots!).default;
}
{
const __VLS_274 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_275 = __VLS_elementAsFunctionalComponent(__VLS_274);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_276 = __VLS_275({ ...{}, style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_275));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_274, typeof __VLS_276> & Record<string, unknown>) => void)({ ...{}, style: ({}), });
const __VLS_277 = __VLS_pickFunctionalComponentCtx(__VLS_274, __VLS_276)!;
let __VLS_278!: __VLS_NormalizeEmits<typeof __VLS_277.emit>;
{
const __VLS_279 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_280 = __VLS_elementAsFunctionalComponent(__VLS_279);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_281 = __VLS_280({ ...{}, class: ("el-card-messages"), }, ...__VLS_functionalComponentArgsRest(__VLS_280));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_279, typeof __VLS_281> & Record<string, unknown>) => void)({ ...{}, class: ("el-card-messages"), });
const __VLS_282 = __VLS_pickFunctionalComponentCtx(__VLS_279, __VLS_281)!;
let __VLS_283!: __VLS_NormalizeEmits<typeof __VLS_282.emit>;
{
const __VLS_284 = ({} as __VLS_IntrinsicElements)["div"];
const __VLS_285 = __VLS_elementAsFunctionalComponent(__VLS_284);
({} as __VLS_IntrinsicElements).div;
({} as __VLS_IntrinsicElements).div;
const __VLS_286 = __VLS_285({ ...{}, id: ("targetElementId"), }, ...__VLS_functionalComponentArgsRest(__VLS_285));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_284, typeof __VLS_286> & Record<string, unknown>) => void)({ ...{}, id: ("targetElementId"), });
const __VLS_287 = __VLS_pickFunctionalComponentCtx(__VLS_284, __VLS_286)!;
let __VLS_288!: __VLS_NormalizeEmits<typeof __VLS_287.emit>;
{
const __VLS_289 = ({} as __VLS_IntrinsicElements)["span"];
const __VLS_290 = __VLS_elementAsFunctionalComponent(__VLS_289);
({} as __VLS_IntrinsicElements).span;
({} as __VLS_IntrinsicElements).span;
const __VLS_291 = __VLS_290({ ...{}, class: ("demonstration"), }, ...__VLS_functionalComponentArgsRest(__VLS_290));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_289, typeof __VLS_291> & Record<string, unknown>) => void)({ ...{}, class: ("demonstration"), });
const __VLS_292 = __VLS_pickFunctionalComponentCtx(__VLS_289, __VLS_291)!;
let __VLS_293!: __VLS_NormalizeEmits<typeof __VLS_292.emit>;
(__VLS_292.slots!).default;
}
{
let __VLS_294!: 'ElRate' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElRate : 'elRate' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elRate : 'el-rate' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-rate'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElRate'];
const __VLS_295 = __VLS_asFunctionalComponent(__VLS_294, new __VLS_294({ ...{}, modelValue: ((__VLS_ctx.score)), }));
({} as { ElRate: typeof __VLS_294; }).ElRate;
const __VLS_296 = __VLS_295({ ...{}, modelValue: ((__VLS_ctx.score)), }, ...__VLS_functionalComponentArgsRest(__VLS_295));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_294, typeof __VLS_296> & Record<string, unknown>) => void)({ ...{}, modelValue: ((__VLS_ctx.score)), });
const __VLS_297 = __VLS_pickFunctionalComponentCtx(__VLS_294, __VLS_296)!;
let __VLS_298!: __VLS_NormalizeEmits<typeof __VLS_297.emit>;
}
(__VLS_287.slots!).default;
}
{
let __VLS_299!: 'ElInput' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElInput : 'elInput' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elInput : 'el-input' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-input'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElInput'];
const __VLS_300 = __VLS_asFunctionalComponent(__VLS_299, new __VLS_299({ ...{}, type: ("textarea"), rows: ((5)), placeholder: ("請輸入留言"), maxlength: ("200"), modelValue: ((__VLS_ctx.content)), style: ({}), }));
({} as { ElInput: typeof __VLS_299; }).ElInput;
({} as { ElInput: typeof __VLS_299; }).ElInput;
const __VLS_301 = __VLS_300({ ...{}, type: ("textarea"), rows: ((5)), placeholder: ("請輸入留言"), maxlength: ("200"), modelValue: ((__VLS_ctx.content)), style: ({}), }, ...__VLS_functionalComponentArgsRest(__VLS_300));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_299, typeof __VLS_301> & Record<string, unknown>) => void)({ ...{}, type: ("textarea"), rows: ((5)), placeholder: ("請輸入留言"), maxlength: ("200"), modelValue: ((__VLS_ctx.content)), style: ({}), });
const __VLS_302 = __VLS_pickFunctionalComponentCtx(__VLS_299, __VLS_301)!;
let __VLS_303!: __VLS_NormalizeEmits<typeof __VLS_302.emit>;
}
{
let __VLS_304!: 'ElButton' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElButton : 'elButton' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elButton : 'el-button' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-button'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElButton'];
const __VLS_305 = __VLS_asFunctionalComponent(__VLS_304, new __VLS_304({ ...{ onClick: {} as any, }, type: ("info"), round: (true), class: ("submit-message"), }));
({} as { ElButton: typeof __VLS_304; }).ElButton;
({} as { ElButton: typeof __VLS_304; }).ElButton;
const __VLS_306 = __VLS_305({ ...{ onClick: {} as any, }, type: ("info"), round: (true), class: ("submit-message"), }, ...__VLS_functionalComponentArgsRest(__VLS_305));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_304, typeof __VLS_306> & Record<string, unknown>) => void)({ ...{ onClick: {} as any, }, type: ("info"), round: (true), class: ("submit-message"), });
const __VLS_307 = __VLS_pickFunctionalComponentCtx(__VLS_304, __VLS_306)!;
let __VLS_308!: __VLS_NormalizeEmits<typeof __VLS_307.emit>;
let __VLS_309 = { 'click': __VLS_pickEvent(__VLS_308['click'], ({} as __VLS_FunctionalComponentProps<typeof __VLS_305, typeof __VLS_306>).onClick) };
__VLS_309 = {
click: $event => {
__VLS_ctx.createOrUpdateMessageAndScore();
}
};
(__VLS_307.slots!).default;
}
{
let __VLS_310!: 'ElButton' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElButton : 'elButton' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elButton : 'el-button' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-button'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElButton'];
const __VLS_311 = __VLS_asFunctionalComponent(__VLS_310, new __VLS_310({ ...{ onClick: {} as any, }, type: ("info"), round: (true), class: ("submit-message"), }));
({} as { ElButton: typeof __VLS_310; }).ElButton;
({} as { ElButton: typeof __VLS_310; }).ElButton;
const __VLS_312 = __VLS_311({ ...{ onClick: {} as any, }, type: ("info"), round: (true), class: ("submit-message"), }, ...__VLS_functionalComponentArgsRest(__VLS_311));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_310, typeof __VLS_312> & Record<string, unknown>) => void)({ ...{ onClick: {} as any, }, type: ("info"), round: (true), class: ("submit-message"), });
const __VLS_313 = __VLS_pickFunctionalComponentCtx(__VLS_310, __VLS_312)!;
let __VLS_314!: __VLS_NormalizeEmits<typeof __VLS_313.emit>;
let __VLS_315 = { 'click': __VLS_pickEvent(__VLS_314['click'], ({} as __VLS_FunctionalComponentProps<typeof __VLS_311, typeof __VLS_312>).onClick) };
__VLS_315 = {
click: $event => {
__VLS_ctx.CancelCreate();
}
};
(__VLS_313.slots!).default;
}
(__VLS_282.slots!).default;
}
(__VLS_277.slots!).default;
}
(__VLS_190.slots!).default;
}
{
let __VLS_316!: 'ElAlert' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.ElAlert : 'elAlert' extends keyof typeof __VLS_ctx ? typeof __VLS_ctx.elAlert : 'el-alert' extends keyof typeof __VLS_ctx ? (typeof __VLS_ctx)['el-alert'] : (typeof __VLS_resolvedLocalAndGlobalComponents)['ElAlert'];
const __VLS_317 = __VLS_asFunctionalComponent(__VLS_316, new __VLS_316({ ...{}, title: ("評分跟評論都要輸入唷!!!"), type: ("warning"), }));
({} as { ElAlert: typeof __VLS_316; }).ElAlert;
const __VLS_318 = __VLS_317({ ...{}, title: ("評分跟評論都要輸入唷!!!"), type: ("warning"), }, ...__VLS_functionalComponentArgsRest(__VLS_317));
({} as (props: __VLS_FunctionalComponentProps<typeof __VLS_316, typeof __VLS_318> & Record<string, unknown>) => void)({ ...{}, title: ("評分跟評論都要輸入唷!!!"), type: ("warning"), });
const __VLS_319 = __VLS_pickFunctionalComponentCtx(__VLS_316, __VLS_318)!;
let __VLS_320!: __VLS_NormalizeEmits<typeof __VLS_319.emit>;
__VLS_directiveFunction(__VLS_ctx.vShow)((__VLS_ctx.isShowScoreAndMessage));
}
if (typeof __VLS_styleScopedClasses === 'object' && !Array.isArray(__VLS_styleScopedClasses)) {
__VLS_styleScopedClasses["card"];
__VLS_styleScopedClasses["mb-3"];
__VLS_styleScopedClasses["left"];
__VLS_styleScopedClasses["row"];
__VLS_styleScopedClasses["g-0"];
__VLS_styleScopedClasses["pic"];
__VLS_styleScopedClasses["card-title"];
__VLS_styleScopedClasses["card-title"];
__VLS_styleScopedClasses["card-title"];
__VLS_styleScopedClasses["card-title"];
__VLS_styleScopedClasses["card"];
__VLS_styleScopedClasses["mb-3"];
__VLS_styleScopedClasses["row"];
__VLS_styleScopedClasses["g-0"];
__VLS_styleScopedClasses["card"];
__VLS_styleScopedClasses["mb-3"];
__VLS_styleScopedClasses["row"];
__VLS_styleScopedClasses["g-0"];
__VLS_styleScopedClasses["col-md-4"];
__VLS_styleScopedClasses["col-md-8"];
__VLS_styleScopedClasses["card-body"];
__VLS_styleScopedClasses["card-title"];
__VLS_styleScopedClasses["el-card-d"];
__VLS_styleScopedClasses["infinite-list-wrapper"];
__VLS_styleScopedClasses["el-card-m"];
__VLS_styleScopedClasses["demonstration"];
__VLS_styleScopedClasses["top-right-button"];
__VLS_styleScopedClasses["top-right-button2"];
__VLS_styleScopedClasses["el-card-messages"];
__VLS_styleScopedClasses["demonstration"];
__VLS_styleScopedClasses["submit-message"];
__VLS_styleScopedClasses["submit-message"];
}
var __VLS_slots!: {};
// @ts-ignore
[score, score, score, content, content, content, createOrUpdateMessageAndScore, CancelCreate, isShowScoreAndMessage,];
return __VLS_slots;
}
