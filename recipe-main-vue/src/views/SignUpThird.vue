<template>
  <h2>註冊畫面3</h2>
  <div class="row" >
    <div class="col-4">
      <div id="list-example" class="list-group">
        <a class="list-group-item list-group-item-action" href="#list-item-1"
          >Item 1</a
        >
        <a class="list-group-item list-group-item-action" href="#list-item-2"
          >Item 2</a
        >
        <a class="list-group-item list-group-item-action" href="#list-item-3"
          >Item 3</a
        >
        <a class="list-group-item list-group-item-action" href="#list-item-4"
          >Item 4</a
        >
      </div>
    </div>
    <div class="col-8 " >
      <div
        data-bs-spy="scroll"
        data-bs-target="#list-example"
        data-bs-smooth-scroll="true"
        class="scrollspy-example"
        tabindex="0"
      >
        <h4 id="list-item-1">Item 1</h4>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt rem sed
          ducimus doloribus molestiae ipsa, reprehenderit nisi accusamus aliquid
          facilis neque sapiente corrupti est impedit quis! Distinctio itaque
          dolores officia.
        </p>
        <h4 id="list-item-2">Item 2</h4>
        <p>
          Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quaerat odit
          sit recusandae laudantium nemo dolorem officiis veritatis fugit ipsam
          doloribus? Quo, non animi velit deserunt aperiam eaque reiciendis odit
          ea. A animi, enim ut, ullam eveniet quaerat cupiditate aliquam quos
          vero doloribus accusantium nisi facilis accusamus voluptatem quae
          minus officia recusandae blanditiis! Neque, temporibus perferendis eum
          excepturi vero reiciendis minima. Vero, iste. Saepe modi fugit aperiam
          distinctio! Reiciendis sequi minima numquam corporis non omnis, a
          consequuntur at excepturi, cupiditate porro error impedit neque libero
          accusamus tempora perferendis assumenda quaerat totam. Porro, soluta
          sit. Quis, iste facere! Non quasi id molestiae, saepe eos error
          similique illum vitae dolor recusandae excepturi tenetur. Porro eum
          commodi labore voluptas odit modi provident adipisci facilis! Aliquam
          mollitia distinctio, aspernatur dicta officiis doloribus atque sunt?
          Corporis nulla tenetur quae. Sit ducimus repellat id? Tempora odio
          nisi accusamus aperiam qui voluptates laboriosam at sed dolores.
          Provident, sed. Sit assumenda accusantium placeat ducimus quaerat
          ratione quo doloribus voluptatibus vel dolor inventore hic ut, unde
          officiis excepturi asperiores eius et sunt aperiam! Placeat totam sit
          deleniti? Ullam, tenetur odit! Expedita ut quaerat incidunt qui
          tempore saepe odio officia accusantium sequi ducimus ab enim eveniet
          minima tenetur nostrum ipsa velit, porro hic maxime corrupti,
          repellendus ipsam. Sunt eligendi vel molestiae. Illum voluptate
          voluptatibus suscipit deleniti quia magni fugit temporibus saepe
          excepturi tenetur dolores ipsam consectetur neque reprehenderit,
          accusamus consequatur minima quibusdam? Magnam, alias. Nostrum autem
          animi qui id ullam dicta. Numquam nobis quam reprehenderit sunt
          aspernatur animi reiciendis rem omnis ullam hic illo facilis quaerat,
          dolor illum voluptatibus minus consequatur iste recusandae quae
          commodi fugiat. Voluptates sint numquam veniam laboriosam! Similique
          aliquid nulla, reprehenderit saepe eaque asperiores dolores assumenda
          labore voluptates nam sit dignissimos veniam at error provident ipsum
          reiciendis, facere nihil cumque omnis! Tempora dolorum mollitia eum
          sint enim.
        </p>
        <h4 id="list-item-3">Item 3</h4>
        <p>...</p>
        <h4 id="list-item-4">Item 4</h4>
        <p>...</p>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-3">
      <RouterLink to="/sign-up-second" class="nav-link">
        <button class="btn btn-primary" type="button" @click="">上一步</button>
      </RouterLink>
    </div>
    <div class="col-3">
      <RouterLink to="/sign-up-fourth" class="nav-link">
        <button class="btn btn-primary" type="button" @click="">下一步</button>
      </RouterLink>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped></style>
